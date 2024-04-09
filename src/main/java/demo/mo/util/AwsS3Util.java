package demo.mo.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import demo.mo.config.DataAccessConfig;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.exception.SdkClientException;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.http.SdkHttpClient;
import software.amazon.awssdk.http.apache.ApacheHttpClient;
import software.amazon.awssdk.services.s3.S3AsyncClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.CopyObjectRequest;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.NoSuchKeyException;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;
import software.amazon.awssdk.services.s3.paginators.ListObjectsV2Iterable;
import software.amazon.awssdk.transfer.s3.S3TransferManager;
import software.amazon.awssdk.transfer.s3.model.Copy;
import software.amazon.awssdk.transfer.s3.model.CopyRequest;
import software.amazon.awssdk.transfer.s3.model.DirectoryDownload;
import software.amazon.awssdk.transfer.s3.model.DownloadDirectoryRequest;
import software.amazon.awssdk.transfer.s3.model.DownloadFileRequest;
import software.amazon.awssdk.transfer.s3.model.FileDownload;
import software.amazon.awssdk.transfer.s3.model.FileUpload;
import software.amazon.awssdk.transfer.s3.model.UploadFileRequest;

/**
 * AWS S3 ユティリティ
 * @author zchang4
 *
 */
public final class AwsS3Util {
    private SdkHttpClient apacheHttpClient;

    private S3Client s3Client;

    private static AwsS3Util instance;

    private S3AsyncClient s3AsyncClient;

    private volatile boolean renewFlg = true;

    private final int sleepUnit = 5;

    /**
     * コンストラクタ
     */
    private AwsS3Util() {
		for (int i = 0; i <= 3; i++) {
			try {
				apacheHttpClient = ApacheHttpClient.builder().maxConnections(DataAccessConfig.getMaxconnections())
						.tcpKeepAlive(true)
						.connectionTimeout(Duration.ofSeconds(DataAccessConfig.getConnectiontimeout())).build();
				s3Client = S3Client.builder().httpClient(apacheHttpClient).build();
				s3AsyncClient = S3AsyncClient.create();
			} catch (SdkClientException e) {
				LogUtil.outAppWarlog("\t\t\t" +  "S3に接続失敗：" + getStackTrace(e));
				if (i == 3) {
					throw e;
				}
				try {
		            Thread.sleep(getSleepTime(i));
		            LogUtil.outAppWarlog("\t\t\t" + String.valueOf(i + 1) + "回目リトライ");
		        } catch (InterruptedException e1) {
		        }
			}
		}

    }

    /**
     * s3AsyncClient刷新
     * @param sleepTime
     */
    private void renewS3AsyncClient(int sleepTime) {
        if (renewFlg) {
            renewFlg = false;
            try {
                s3AsyncClient.close();

            } catch (Exception e) {

            }
            try {
                s3AsyncClient = S3AsyncClient.create();
            } finally {
                renewFlg = true;
            }

        }
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e1) {
        }
    }

    /**
     * s3Client刷新
     * @param sleepTime
     */
    private void renewS3Client(int sleepTime, int i) {
    	try {
            Thread.sleep(sleepTime);
            LogUtil.outAppWarlog("\t\t\t" + String.valueOf(i + 1) + "回目リトライ");
        } catch (InterruptedException e1) {
        }
    	 try {
    		 try {
    			 if (apacheHttpClient != null) {
    				 apacheHttpClient.close();

    			 }
             } catch (Exception e) {

             }
             try {
            	 if (s3Client != null) {
            		 s3Client.close();
            	 }
                 
             } catch (Exception e) {

             }
             apacheHttpClient = ApacheHttpClient.builder().maxConnections(DataAccessConfig.getMaxconnections())
                     .tcpKeepAlive(true)
                     .connectionTimeout(Duration.ofSeconds(DataAccessConfig.getConnectiontimeout())).build();
             s3Client = S3Client.builder().httpClient(apacheHttpClient).build();
         } catch (Exception e) {
         } 

    }

    /**
     * インスタンス取得
     * @return インスタンス
     */
    public static AwsS3Util getInstance() {
        if (instance == null) {
            instance = new AwsS3Util();
        }

        return instance;
    }

    /**
     * 指定のオブジェクトを削除する
     *
     * @param bucketName バケット名
     * @param key        オブジェクトのキー名
     */
    public void deleteObject(String bucketName, String key) {
        for (int i = 0; i <= 3; i++) {
            try {
                S3Client client = AwsS3Util.getInstance().s3Client;

                DeleteObjectRequest request = DeleteObjectRequest.builder().bucket(bucketName).key(key).build();

                client.deleteObject(request);
                break;
            } catch (S3Exception e) {
                LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + key + "が削除失敗:" + getStackTrace(e));
                LogUtil.outAppWarlog(
                        "\t\t\t" + bucketName + ":" + key + "が削除失敗。S3 Extended Request ID:" + e.extendedRequestId());
                if (e instanceof NoSuchKeyException) {
                    throw e;
                }
                if (i == 3) {
                    throw e;
                }
                renewS3Client(getSleepTime(i), i);
            } catch (SdkClientException e) {
                LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + key + "が削除失敗:" + getStackTrace(e));
                if (i == 3) {
                    throw e;
                }
                renewS3Client(getSleepTime(i), i);
            }
        }

    }

    /**
     * 指定プレフィックスに指定ファイルのプレフィックスのオブジェクトを削除する
     * @param bucketName バケット名
     * @param prefix S3のプレフィックス
     * @param filePrefixex ファイルのプレフィックス
     * @param doesIncludeSub サブパスにのオブジェクト削除フラグ
     */
    public void deleteObjects(String bucketName, String prefix, boolean doesIncludeSub, String... filePrefixex) {
        List<String> delKeys = getKeys(bucketName, prefix, doesIncludeSub, filePrefixex);

        for (String delKey : delKeys) {
            deleteObject(bucketName, delKey);
        }
    }

    /**
     * オブジェクトのキーを取得する
     *
     * @param bucketName     バケット名
     * @param prefix         プレフィックス
     * @param filePrefixes   ファイル名のプレフィックス
     * @param doesIncludeSub サブパスの含むフラグ
     * @return オブジェクトのキー
     */
    public List<String> getKeys(String bucketName, String prefix, boolean doesIncludeSub, String... filePrefixes) {
        List<String> objects = new ArrayList<String>();

        boolean hasFilePrefix = false;
        if (filePrefixes != null && filePrefixes.length > 0) {
            for (String filePrefix : filePrefixes) {
                if (filePrefix != null && !filePrefix.trim().isEmpty()) {
                    hasFilePrefix = true;
                    break;
                }
            }
        }

        ListObjectsV2Request.Builder builder = ListObjectsV2Request.builder().bucket(bucketName);
        if (prefix != null && !prefix.isEmpty()) {
            builder.prefix(prefix);
        }
        if (!doesIncludeSub) {
            builder.delimiter(prefix);
        }
        ListObjectsV2Request listReq = builder.maxKeys(1).build();
        ListObjectsV2Iterable listRes = s3Client.listObjectsV2Paginator(listReq);
        final boolean hasFlag = hasFilePrefix;
        listRes.contents().stream().forEach(content -> {
            if (hasFlag) {
                String objKey = content.key();
                String filePrefix;
                for (int i = 0; i < filePrefixes.length; i++) {
                    filePrefix = filePrefixes[i] == null ? "" : filePrefixes[i].trim();
                    if (!filePrefix.isEmpty() && objKey.substring(objKey.lastIndexOf("/") + 1).startsWith(filePrefix)) {
                        objects.add(objKey);
                    }
                }

            } else {
                objects.add(content.key());
            }
        });
        return objects;
    }

    /**
     * 指定オブジェクトのサイズを取得する
     *
     * @param bucketName バケット名
     * @param key        オブジェクトのキー名
     * @return 指定オブジェクトのサイズ
     */
    public long getObjectSize(String bucketName, String key) {
        try {
            HeadObjectRequest headObjectRequest = HeadObjectRequest.builder().bucket(bucketName).key(key).build();
            HeadObjectResponse headObjectResponse = s3Client.headObject(headObjectRequest);
            long objectSize = headObjectResponse.contentLength();
            return objectSize;
        } catch (S3Exception e) {
            LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + key + "がサイズ取得失敗:" + e.getStackTrace());
            LogUtil.outAppWarlog(
                    "\t\t\t" + bucketName + ":" + key + "がサイズ取得失敗。S3 Extended Request ID:" + e.extendedRequestId());
            throw e;

        }
    }

    /**
     * S3からディレクトリごとにダウンロードする。
     *
     * @param bucketName
     * @param keyPrefix
     * @param dirPath
     * @param includes
     * @throws InterruptedException
     * @throws AmazonClientException
     * @throws AmazonServiceException
     */
    public void downloadDir(String bucketName, String keyPrefix, String dirPath, String includes) {
        for (int i = 0; i <= 3; i++) {
            try {
                S3TransferManager transferManager = S3TransferManager.builder().s3Client(s3AsyncClient).build();
                DirectoryDownload directoryDownload = transferManager.downloadDirectory(DownloadDirectoryRequest
                        .builder().destination(Paths.get(dirPath)).bucket(bucketName)
                        .listObjectsV2RequestTransformer(request -> request.prefix(keyPrefix)).filter(s3object -> {
                            for (String include : includes.split(";")) {
                                if (Pattern.matches(include, s3object.key())) {
                                    return true;
                                }
                            }
                            return false;
                        }).build());
                directoryDownload.completionFuture().join();
                transferManager.close();
                break;
            } catch (S3Exception e) {
                LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + keyPrefix + "がダウンロード失敗:" + getStackTrace(e));
                LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + keyPrefix + "がダウンロード失敗。S3 Extended Request ID:"
                        + e.extendedRequestId());
                if (e instanceof NoSuchKeyException) {
                    throw e;
                }
                if (i == 3) {
                    throw e;
                }
                LogUtil.outAppWarlog("\t\t\t" + String.valueOf(i + 1) + "回目リトライ");
                renewS3AsyncClient(getSleepTime(i));

            } catch (SdkClientException e) {
            	LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + keyPrefix + "がダウンロード失敗:" + getStackTrace(e));
                if (i == 3) {
                    throw e;
                }
                LogUtil.outAppWarlog("\t\t\t" + String.valueOf(i + 1) + "回目リトライ");
                renewS3Client(getSleepTime(i), i);
            }
        }

    }

    /**
     * ダウンロード処理
     *
     * @param bucketName バケット名
     * @param key        オブジェクトのキー名
     * @return バイトバフア
     * @throws IOException
     */
    public ByteBuffer download(String bucketName, String key) {
        ByteBuffer byteBuffer = null;
        try {
            for (int i = 0; i <= 3; i++) {
                try {
                    GetObjectRequest objectRequest = GetObjectRequest.builder().key(key).bucket(bucketName).build();
                    ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(objectRequest);
                    byte[] data = objectBytes.asByteArray();
                    byteBuffer = ByteBuffer.allocate(data.length);
                    byteBuffer.put(data);
                    byteBuffer.flip();
                    break;

                } catch (S3Exception e) {
                    LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + key + "がダウンロード失敗:" + getStackTrace(e));
                    LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + key + "がダウンロード失敗。S3 Extended Request ID:"
                            + e.extendedRequestId());
                    if (e instanceof NoSuchKeyException) {
                        throw e;
                    }
                    if (i == 3) {
                        throw e;
                    }
                    renewS3Client(getSleepTime(i),i);
                } catch (SdkClientException e) {
                	LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + key + "がダウンロード失敗:" + getStackTrace(e));
                    if (i == 3) {
                        throw e;
                    }
                    renewS3Client(getSleepTime(i), i);
                }
            }
            return byteBuffer;
        } catch (S3Exception e) {
            throw e;
        }

    }

    /**
     * ダウンロード処理
     *
     * @param bucketName       バケット名
     * @param key              オブジェクトのキー名
     * @param downloadFilePath ダウンロードファイルのローカルパス
     * @throws IOException
     */
    public void downloadAsync(String bucketName, String key, String downloadFilePath) {
        for (int i = 0; i <= 3; i++) {
            try {
                S3TransferManager transferManager = S3TransferManager.builder().s3Client(s3AsyncClient).build();

                FileDownload filedownload = transferManager
                        .downloadFile(DownloadFileRequest.builder().getObjectRequest(b -> b.bucket(bucketName).key(key))
                                .destination(Paths.get(downloadFilePath)).build());
                filedownload.completionFuture().join();
                transferManager.close();
                break;
            } catch (S3Exception e) {
                LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + key + "がダウンロード失敗:" + getStackTrace(e));
                LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + key + "がダウンロード失敗。S3 Extended Request ID:"
                        + e.extendedRequestId());
                if (e instanceof NoSuchKeyException) {
                    throw e;
                }
                if (i == 3) {
                    throw e;
                }
                LogUtil.outAppWarlog("\t\t\t" + String.valueOf(i + 1) + "回目リトライ");
                renewS3AsyncClient(getSleepTime(i));
            } catch (SdkClientException e) {
            	LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + key + "がダウンロード失敗:" + getStackTrace(e));
                if (i == 3) {
                    throw e;
                }
//                LogUtil.outAppWarlog("\t\t\t" + String.valueOf(i + 1) + "回目リトライ");
                renewS3Client(getSleepTime(i), i);
            }

        }

    }

    /**
     * S3から単件ファイルをダウンロードする
     *
     * @param bucketName
     * @param keyName
     * @param filePath
     * @throws IOException
     */
    public void downloadFile(String bucketName, String keyName, String filePath) throws IOException {
        for (int i = 0; i <= 3; i++) {
            try (OutputStream os = new FileOutputStream(filePath)) {
                GetObjectRequest objectRequest = GetObjectRequest.builder().key(keyName).bucket(bucketName).build();
                ResponseBytes<GetObjectResponse> objectBytes = s3Client.getObjectAsBytes(objectRequest);
                byte[] data = objectBytes.asByteArray();
                os.write(data);
                break;
            } catch (IOException ex) {
                LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + keyName + "がダウンロード失敗:" + getStackTrace(ex));
                throw ex;
            } catch (S3Exception e) {
                LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + keyName + "がダウンロード失敗:" + getStackTrace(e));
                LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + keyName + "がダウンロード失敗。S3 Extended Request ID:"
                        + e.extendedRequestId());
                if (e instanceof NoSuchKeyException) {
                    throw e;
                }
                if (i == 3) {
                    throw e;
                }
                renewS3Client(getSleepTime(i), i);
            } catch (SdkClientException e) {
            	LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + keyName + "がダウンロード失敗:" + getStackTrace(e));
                if (i == 3) {
                    throw e;
                }
                renewS3Client(getSleepTime(i), i);
            }
        }

    }

    /**
     * フォルダ作成
     * @param bucketName
     * @param keyName フォルダ名
     */
    public void addDir(String bucketName, String keyName) {
        try {
            PutObjectRequest putOb = PutObjectRequest.builder().bucket(bucketName).key(keyName + "/").build();
            RequestBody requestBody = RequestBody.empty();
            s3Client.putObject(putOb, requestBody);
        } catch (Exception e) {
        }
    }
    /**
     * S3にファイルをアップロートする。
     *
     * @param bucketName
     * @param keyName
     * @param filePath
     */
    public void uploadFile(String bucketName, String keyName, String filePath) {
        for (int i = 0; i <= 3; i++) {
            try {
                PutObjectRequest putOb = PutObjectRequest.builder().bucket(bucketName).key(keyName).build();

                s3Client.putObject(putOb, RequestBody.fromFile(new File(filePath)));
                break;

            } catch (S3Exception e) {
                LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + keyName + "がアップロート:" + getStackTrace(e));
                LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + keyName + "がアップロート。S3 Extended Request ID:"
                        + e.extendedRequestId());
                if (e instanceof NoSuchKeyException) {
                    throw e;
                }
                if (i == 3) {
                    throw e;
                }
                renewS3Client(getSleepTime(i), i);
            } catch (SdkClientException e) {
            	LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + keyName + "がアップロート:" + getStackTrace(e));
                if (i == 3) {
                    throw e;
                }
                renewS3Client(getSleepTime(i), i);
            }
        }

    }

    /**
     * S3にファイルをアップロートする。
     *
     * @param s3Path
     * @param filePath
     * @throws AmazonServiceException
     * @throws AmazonClientException
     * @throws InterruptedException
     */
    public void uploadFile(String s3Path, String filePath) {
        String[] bucketAndKey = s3Path.split("\\|");
        this.uploadFile(bucketAndKey[0], bucketAndKey[1], filePath);
    }

    /**
     * S3にファイルをアップロートする。
     *
     * @param bucketName
     * @param keyName
     * @param filePath
     */
    public void uploadFileAsync(String bucketName, String keyName, String filePath) {
        for (int i = 0; i <= 3; i++) {
            try {
                S3TransferManager transferManager = S3TransferManager.builder().s3Client(s3AsyncClient).build();

                FileUpload fileupload = transferManager.uploadFile(
                        UploadFileRequest.builder().putObjectRequest(req -> req.bucket(bucketName).key(keyName))
                                .source(Paths.get(filePath)).build());
                fileupload.completionFuture().join();
                transferManager.close();
                break;
            } catch (S3Exception e) {
                LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + keyName + "がアップロート失敗:" + getStackTrace(e));
                LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + keyName + "がアップロート失敗。S3 Extended Request ID:"
                        + e.extendedRequestId());
                if (e instanceof NoSuchKeyException) {
                    throw e;
                }
                if (i == 3) {
                    throw e;
                }
                LogUtil.outAppWarlog("\t\t\t" + String.valueOf(i + 1) + "回目リトライ");
                renewS3AsyncClient(getSleepTime(i));
            } catch (SdkClientException e) {
            	LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + keyName + "がアップロート失敗:" + getStackTrace(e));
                if (i == 3) {
                    throw e;
                }
                renewS3Client(getSleepTime(i), i);
            }
        }

    }

    /**
     * オブジェクトをコピーする。
     *
     * @param sourceBucket
     * @param sourceKey
     * @param destinationBucket
     * @param destinationKey
     * @throws InterruptedException
     */
    public void copyObject(String sourceBucket, String sourceKey, String destinationBucket, String destinationKey) {
        for (int i = 0; i <= 3; i++) {
            try {
                CopyObjectRequest copyReq = CopyObjectRequest.builder().sourceBucket(sourceBucket).sourceKey(sourceKey)
                        .destinationBucket(destinationBucket).destinationKey(destinationKey).build();
                s3Client.copyObject(copyReq);
                break;

            } catch (S3Exception e) {
                LogUtil.outAppWarlog("\t\t\t" + sourceBucket + ":" + sourceKey + ":" + destinationBucket + ":"
                        + destinationKey + "オブジェクトコピー失敗しました:" + getStackTrace(e));
                LogUtil.outAppWarlog("\t\t\t" + sourceBucket + ":" + sourceKey + ":" + destinationBucket + ":"
                        + destinationKey + "オブジェクトコピー失敗しました。S3 Extended Request ID:" + e.extendedRequestId());
                if (e instanceof NoSuchKeyException) {
                    throw e;
                }
                if (i == 3) {
                    throw e;
                }
                renewS3Client(getSleepTime(i), i);
            } catch (SdkClientException e) {
            	LogUtil.outAppWarlog("\t\t\t" + sourceBucket + ":" + sourceKey + ":" + destinationBucket + ":"
                        + destinationKey + "オブジェクトコピー失敗しました:" + getStackTrace(e));
                if (i == 3) {
                    throw e;
                }
                renewS3Client(getSleepTime(i), i);
            }
        }

    }

    /**
     * オブジェクト名をリネームする。
     *
     * @param s3Path              S3フールパス(バケット＋ファイルパス＋ファイル名)
     * @param destinationFileName
     */
    public void renameObject(String s3Path, String destinationFileName) {
        String[] bucketAndKey = s3Path.split("\\|");
        renameObject(bucketAndKey[0], bucketAndKey[1], destinationFileName);
    }

    /**
     * オブジェクトをコピーする。
     *
     * @param sourceBucket
     * @param sourceKey
     * @param destinationKey
     * @throws InterruptedException
     */
    public void copyObject(String sourceBucket, String sourceKey, String destinationKey) {
        copyObject(sourceBucket, sourceKey, sourceBucket, destinationKey);
    }

    /**
     * オブジェクト名をリネームする。
     *
     * @param bucketName
     * @param sourceKey
     * @param destinationKey
     */
    public void renameObject(String bucketName, String sourceKey, String destinationKey) {
        copyObject(bucketName, sourceKey, destinationKey);
        for (int i = 0; i <= 3; i++) {
            try {
                deleteObject(bucketName, sourceKey);
                break;
            } catch (Exception ex) {
                LogUtil.outAppWarlog("\t\t\t" + bucketName + ":" + sourceKey + "が削除失敗。" + LogUtil.getStackTrace(ex));
                if (i == 3) {
                    break;
                }
                try {
                    Thread.sleep(getSleepTime(i));
                } catch (InterruptedException e) {
                }
            }
        }
    }

    /**
     * オブジェクトをコピーする。
     *
     * @param sourceBucket
     * @param sourceKey
     * @param destinationBucket
     * @param destinationKey
     * @throws InterruptedException
     */
    public void copyObjectAsync(String sourceBucket, String sourceKey, String destinationBucket,
            String destinationKey) {
        for (int i = 0; i <= 3; i++) {
            try {
                S3TransferManager transferManager = S3TransferManager.builder().s3Client(s3AsyncClient).build();
                CopyObjectRequest copyObjectRequest = CopyObjectRequest.builder().sourceBucket(sourceBucket)
                        .sourceKey(sourceKey).destinationBucket(destinationBucket).destinationKey(destinationKey)
                        .build();
                Copy copy = transferManager.copy(CopyRequest.builder().copyObjectRequest(copyObjectRequest).build());
                copy.completionFuture().join();
                transferManager.close();
            } catch (S3Exception e) {
                LogUtil.outAppWarlog("\t\t\t" + sourceBucket + ":" + sourceKey + "がアップロート失敗。S3 Extended Request ID:"
                        + e.extendedRequestId());
                if (e instanceof NoSuchKeyException) {
                    throw e;
                }
                if (i == 3) {
                    throw e;
                }
                LogUtil.outAppWarlog("\t\t\t" + String.valueOf(i + 1) + "回目リトライ");
                renewS3AsyncClient(getSleepTime(i));
            }
        }

    }

    /**
     * スリーブ時間取得
     * @param i
     * @return 秒数
     */
    private int getSleepTime(int i) {
        return (int) (sleepUnit * Math.pow(2, i) * 1000);
    }

    private String getStackTrace(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        pw.flush();
        return sw.toString();

    }
}
