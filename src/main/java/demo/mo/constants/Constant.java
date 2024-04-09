package demo.mo.constants;

public final class Constant {

    private Constant() {
    }

    /**
     * 商品分類使用フラグ LETSS用
     */
    public static final String SYOHINBUNRUI_LETSS = "2";

    /**
     * TOKEN 変数
     *
     */
    public final class TOKEN {
        private TOKEN() {
        }
//        /** 内部TOKEN環境変数 */
//        public static final String AUTH_TOKEN_ENV = "AUTH_TOKEN";
//        /** 内部TOKENヘッダ */
        public static final String AUTH_TOKEN_HEADER = "Authorization";
    }

    public static final String[] EDIT_AUTH_CONTROLID = {
            "New_Create_Tab_Name",
            "Modify_Tab_Name",
            "Delete_Tab_Name",
            "Register_Modify_Tab_Name",
            "Assum_Food_Tab_Name",
            "New_Food_Tab_Name",
            "DeleteCancel_Tab_Name",
            "Copy_Tab_Name",
            "Add_Product_Tab_Name",
            "Next_Term_Tab_Name"
    };

    public final class LoginErrCode {
        private LoginErrCode() {
        }

        /**
         * ユーザー名取得に失敗しました
         */
        public static final String GET_USER_ERR = "01";

        /**
         * 組織名取得に失敗しました
         */
        public static final String GET_SOSIK_ERR = "02";

        /**
         * 職位名取得に失敗しました
         */
        public static final String GET_SYOKUI_ERR = "03";

        /**
         * 氏名コード
         */
        public static final String INPUT_USER_ERR = "04";

        /**
         * 氏名コード(桁数)
         */
        public static final String INPUT_USER_LENGTH_ERR = "10";

        /**
         * 組織グループコード
         */
        public static final String INPUT_SYGREP_ERR = "05";

        /**
         * 職位グループコード
         */
        public static final String INPUT_SYOKUIGEP_ERR = "06";

        /**
         * 職位コード
         */
        public static final String INPUT_SYOKUI_ERR = "07";

        /**
         * 取引先コード
         */
        public static final String INPUT_TORIHIK_ERR = "08";

        /**
         * 職位コード
         */
        public static final String INPUT_OTHER_ERR = "09";
    }

    /**
     * 採番区分コード
     * マスター種別パラメータ、採番マスターの区分を示すコードと同期する必要がある
     */
    public final class SaibanKubunCode {
        private SaibanKubunCode() {
        }
        /**
        * 裏貼用品名コードを示すパラメータ
        */
        public static final String URA_HINMEI_CODE = "01";
        /**
        * 代表原材料コードを示すパラメータ
        */
        public static final String DAIHYO_GEN_CODE = "03";
        /**
        * 仮包材コードを示すパラメータ
        */
        public static final String KARI_HOUZAI_CODE = "04";
        /**
        * 指定外食材コードを示すパラメータ
        */
        public static final String GAI_SHOKUZAI_CODE = "05";
        /**
        * 指定外包材コードを示すパラメータ
        */
        public static final String GAI_HOUZAI_CODE = "06";
        /**
        * 仕様別商品コードを示すパラメータ
        */
        public static final String SHIYOUBETU_SHOUHIN_CODE = "07";
        /**
        * 生産化効用商品コードを示すパラメータ
        */
        public static final String SEISANKAKOU_SHOUHIN_CODE = "08";
        /**
        * 単価変更申請コードを示すパラメータ
        */
        public static final String TANKA_SINSEI_CODE = "09";
        /**
        * 半製品コードを示すパラメータ
        */
        public static final String HANSEIHIN_CODE = "10";
        /**
        * 品目コードを示すパラメータ
        */
        public static final String HINMOKU_CODE = "11";
        /**
        * 提案コードを示すパラメータ
        */
        public static final String TEIAN_CODE = "13";
        /**
        * 仮食材コードを示すパラメータ
        */
        public static final String KARI_SHOKUZAI_CODE = "14";
    }

    /**
     * 名重複チェックSQLを取得する区分コード
     *
     */
    public final class DuplicationCheckKubunCode {
        private DuplicationCheckKubunCode() {
        }
        /**
        * 代表原材料名重複チェックSQLを取得する際に指定するパラメータ
        */
        public static final int DAIHYO_KBN = 0;
        /**
        * 指定食材名重複チェックSQLを取得する際に指定するパラメータ
        */
        public static final int SHITEI_SYOKUZAI_KBN = 1;
        /**
        * 指定外食材名重複チェックSQLを取得する際に指定するパラメータ
        */
        public static final int SHITEI_GAI_SYOKUZAI_KBN = 2;
        /**
        * 指定外包材名重複チェックSQLを取得する際に指定するパラメータ
        */
        public static final int SHITEI_GAI_HOUZAI_KBN = 3;
        /**
        * 仮包材名重複チェックSQLを取得する際に指定するパラメータ
        */
        public static final int KARI_HOUZAI_KBN = 4;
        /**
        * 生産加工用商品名重複チェックSQLを取得する際に指定するパラメータ
        */
        public static final int SEISANKAKO_SYOHIN_KBN = 5;
        /**
        * 指定包材名重複チェックSQLを取得する際に指定するパラメータ
        */
        public static final int SHITEI_HOUZAI_KBN = 6;
    }

    /**
     * メッセージ 変数
     *
     */
    public final class MESSAGECODE {
        private MESSAGECODE() {
        }
        public static final String M_CM_0001_101 = "M_CM_0001.101";
        public static final String M_CM_0002_102 = "M_CM_0002.102";
        public static final String M_CM_0003_103 = "M_CM_0003.103";
        public static final String M_CM_0004_104 = "M_CM_0004.104";
        public static final String M_CM_0005_105 = "M_CM_0005.105";
        public static final String M_CM_0006_106 = "M_CM_0006.106";
        public static final String M_CM_0007_107 = "M_CM_0007.107";
        public static final String M_CM_0008_108 = "M_CM_0008.108";
        public static final String M_CM_0009_109 = "M_CM_0009.109";
        public static final String M_CM_0012_112 = "M_CM_0012.112";
        public static final String M_CM_0013_113 = "M_CM_0013.113";
        public static final String E_CM_0001_1001 = "E_CM_0001.1001";
        public static final String E_CM_0002_1002 = "E_CM_0002.1002";
        public static final String E_CM_0003_1003 = "E_CM_0003.1003";
        public static final String E_CM_0004_1004 = "E_CM_0004.1004";
        public static final String E_CM_0005_1005 = "E_CM_0005.1005";
        public static final String E_CM_0006_1006 = "E_CM_0006.1006";
        public static final String E_CM_0007_1007 = "E_CM_0007.1007";
        public static final String E_CM_0008_1008 = "E_CM_0008.1008";
        public static final String E_CM_0009_1009 = "E_CM_0009.1009";
        public static final String E_CM_0010_1010 = "E_CM_0010.1010";
        public static final String E_CM_0011_1011 = "E_CM_0011.1011";
        public static final String E_CM_0012_1012 = "E_CM_0012.1012";
        public static final String E_CM_0013_1013 = "E_CM_0013.1013";
        public static final String E_CM_0014_1014 = "E_CM_0014.1014";
        public static final String E_CM_0015_1015 = "E_CM_0015.1015";
        public static final String E_CM_0016_1016 = "E_CM_0016.1016";
        public static final String E_CM_0017_1017 = "E_CM_0017.1017";
        public static final String E_CM_0018_1018 = "E_CM_0018.1018";
        public static final String E_CM_0019_1019 = "E_CM_0019.1019";
        public static final String E_CM_0020_1020 = "E_CM_0020.1020";
        public static final String E_CM_0021_1021 = "E_CM_0021.1021";
        public static final String E_CM_0022_1022 = "E_CM_0022.1022";
        public static final String E_CM_0023_1023 = "E_CM_0023.1023";
        public static final String E_CM_0024_1024 = "E_CM_0024.1024";
        public static final String E_CM_0025_1025 = "E_CM_0025.1025";
        public static final String E_CM_0026_1026 = "E_CM_0026.1026";
        public static final String E_CM_0027_1027 = "E_CM_0027.1027";
        public static final String E_CM_0028_1028 = "E_CM_0028.1028";
        public static final String E_CM_0029_1029 = "E_CM_0029.1029";
        public static final String E_CM_0030_1030 = "E_CM_0030.1030";
        public static final String E_CM_0031_1031 = "E_CM_0031.1031";
        public static final String E_CM_0032_1032 = "E_CM_0032.1032";
        public static final String E_CM_0033_1033 = "E_CM_0033.1033";
        public static final String E_CM_0034_1034 = "E_CM_0034.1034";
        public static final String E_CM_0035_1035 = "E_CM_0035.1035";
        public static final String E_CM_0036_1036 = "E_CM_0036.1036";
        public static final String E_CM_0037_1037 = "E_CM_0037.1037";
        public static final String E_CM_0038_1038 = "E_CM_0038.1038";
        public static final String E_CM_0039_1039 = "E_CM_0039.1039";
        public static final String E_CM_0040_1040 = "E_CM_0040.1040";
        public static final String E_CM_0041_1041 = "E_CM_0041.1041";
        public static final String E_CM_0042_1042 = "E_CM_0042.1042";
        public static final String E_CM_0043_1043 = "E_CM_0043.1043";
        public static final String E_CM_0044_1044 = "E_CM_0044.1044";
        public static final String E_CM_0045_1045 = "E_CM_0045.1045";
        public static final String E_CM_0046_1046 = "E_CM_0046.1046";
        public static final String E_CM_0047_1047 = "E_CM_0047.1047";
        public static final String E_CM_0048_1048 = "E_CM_0048.1048";
        public static final String E_CM_0049_1049 = "E_CM_0049.1049";
        public static final String E_CM_0050_1050 = "E_CM_0050.1050";
        public static final String E_CM_0051_1051 = "E_CM_0051.1051";
        public static final String E_CM_0052_1052 = "E_CM_0052.1052";
        public static final String E_CM_0053_1053 = "E_CM_0053.1053";
        public static final String E_CM_0054_1054 = "E_CM_0054.1054";
        public static final String E_CM_0055_1055 = "E_CM_0055.1055";
        public static final String E_CM_0056_1056 = "E_CM_0056.1056";
        public static final String E_CM_0057_1057 = "E_CM_0057.1057";
        public static final String E_CM_0058_1058 = "E_CM_0058.1058";
        public static final String E_CM_0059_1059 = "E_CM_0059.1059";
        public static final String E_CM_0060_1060 = "E_CM_0060.1060";
        public static final String E_CM_0061_1061 = "E_CM_0061.1061";
        public static final String E_CM_0062_1062 = "E_CM_0062.1062";
        public static final String E_CM_0063_1063 = "E_CM_0063.1063";
        public static final String E_CM_0064_1064 = "E_CM_0064.1064";
        public static final String E_CM_0065_1065 = "E_CM_0065.1065";
        public static final String E_CM_0066_1066 = "E_CM_0066.1066";
        public static final String E_CM_0067_1067 = "E_CM_0067.1067";
        public static final String E_CM_0068_1068 = "E_CM_0068.1068";
        public static final String E_CM_0069_1069 = "E_CM_0069.1069";
        public static final String E_CM_0070_1070 = "E_CM_0070.1070";
        public static final String E_CM_0071_1071 = "E_CM_0071.1071";
        public static final String E_CM_0072_1072 = "E_CM_0072.1072";
        public static final String E_CM_0073_1073 = "E_CM_0073.1073";
        public static final String E_CM_0074_1074 = "E_CM_0074.1074";
        public static final String E_CM_0075_1075 = "E_CM_0075.1075";
        public static final String E_CM_0076_1076 = "E_CM_0076.1076";
        public static final String E_CM_0077_1077 = "E_CM_0077.1077";
        public static final String E_CM_0078_1078 = "E_CM_0078.1078";
        public static final String E_CM_0079_1079 = "E_CM_0079.1079";
        public static final String E_CM_0080_1080 = "E_CM_0080.1080";
        public static final String E_CM_0081_1081 = "E_CM_0081.1081";
        public static final String E_CM_0082_1082 = "E_CM_0082.1082";
        public static final String E_CM_0083_1083 = "E_CM_0083.1083";
        public static final String E_CM_0084_1084 = "E_CM_0084.1084";
        public static final String E_CM_0085_1085 = "E_CM_0085.1085";
        public static final String E_CM_0086_1086 = "E_CM_0086.1086";
        public static final String E_CM_0087_1087 = "E_CM_0087.1087";
        public static final String E_CM_0088_1088 = "E_CM_0088.1088";
        public static final String E_CM_0089_1089 = "E_CM_0089.1089";
        public static final String E_CM_0090_1090 = "E_CM_0090.1090";
        public static final String E_CM_0091_1091 = "E_CM_0091.1091";
        public static final String E_CM_0092_1092 = "E_CM_0092.1092";
        public static final String E_CM_0093_1093 = "E_CM_0093.1093";
        public static final String E_CM_0094_1094 = "E_CM_0094.1094";
        public static final String E_CM_0095_1095 = "E_CM_0095.1095";
        public static final String E_CM_0096_1096 = "E_CM_0096.1096";
        public static final String E_CM_0097_1097 = "E_CM_0097.1097";
        public static final String E_CM_0098_1098 = "E_CM_0098.1098";
        public static final String E_CM_0099_1099 = "E_CM_0099.1099";
        public static final String E_CM_0100_1100 = "E_CM_0100.1100";
        public static final String E_CM_0101_1101 = "E_CM_0101.1101";
        public static final String E_CM_0102_1102 = "E_CM_0102.1102";
        public static final String E_CM_0103_1103 = "E_CM_0103.1103";
        public static final String E_CM_0104_1104 = "E_CM_0104.1104";
        public static final String E_CM_0105_1105 = "E_CM_0105.1105";
        public static final String E_CM_0106_1106 = "E_CM_0106.1106";
        public static final String E_CM_0107_1107 = "E_CM_0107.1107";
        public static final String E_CM_0108_1108 = "E_CM_0108.1108";
        public static final String E_CM_0109_1109 = "E_CM_0109.1109";
        public static final String E_CM_0110_1110 = "E_CM_0110.1110";
        public static final String E_CM_0111_1111 = "E_CM_0111.1111";
        public static final String E_CM_0112_1112 = "E_CM_0112.1112";
        public static final String E_CM_0113_1113 = "E_CM_0113.1113";
        public static final String E_CM_0114_1114 = "E_CM_0114.1114";
        public static final String E_CM_0115_1115 = "E_CM_0115.1115";
        public static final String E_CM_0116_1116 = "E_CM_0116.1116";
        public static final String E_CM_0117_1117 = "E_CM_0117.1117";
        public static final String E_CM_0118_1118 = "E_CM_0118.1118";
        public static final String E_CM_0001_1119 = "E_CM_0001.1119";
        public static final String E_CM_0001_1120 = "E_CM_0001.1120";
        public static final String E_CM_0121_1121 = "E_CM_0121.1121";
        public static final String E_CM_0122_1122 = "E_CM_0122.1122";
        public static final String E_CM_0123_1123 = "E_CM_0123.1123";
        public static final String E_CM_0131_1131 = "E_CM_0131.1131";
        public static final String E_CM_0132_1132 = "E_CM_0132.1132";
        public static final String E_CM_0133_1133 = "E_CM_0133.1133";
        public static final String E_CM_0134_1134 = "E_CM_0134.1134";
        public static final String E_CM_0135_1135 = "E_CM_0135.1135";
        public static final String E_CM_0136_1136 = "E_CM_0136.1136";
        public static final String E_CM_0137_1137 = "E_CM_0137.1137";
        public static final String E_CM_0138_1138 = "E_CM_0138.1138";
        public static final String E_CM8004_8004 = "E_CM8004.8004";
        public static final String E_CM_1201_1201 = "E_CM_1201.1201";
        public static final String E_CM_1202_1202 = "E_CM_1202.1202";
        public static final String E_CM_1203_1203 = "E_CM_1203.1203";
        public static final String E_CM_1204_1204 = "E_CM_1204.1204";
        public static final String E_CM_1205_1205 = "E_CM_1205.1205";
        public static final String E_CM_1206_1206 = "E_CM_1206.1206";
        public static final String E_CM_1207_1207 = "E_CM_1207.1207";
        public static final String E_CM_1208_1208 = "E_CM_1208.1208";
        public static final String E_CM_1209_1209 = "E_CM_1209.1209";
        public static final String E_CM_1210_1210 = "E_CM_1210.1210";
        public static final String E_CM_1211_1211 = "E_CM_1211.1211";
        public static final String E_CM_1212_1212 = "E_CM_1212.1212";
        public static final String E_CM_1213_1213 = "E_CM_1213.1213";
        public static final String E_CM_2001_2001 = "E_CM_2001.2001";
        public static final String E_CM_2002_2002 = "E_CM_2002.2002";
        public static final String E_CM_2003_2003 = "E_CM_2003.2003";
        public static final String E_CM_2004_2004 = "E_CM_2004.2004";
        public static final String E_CM_2005_2005 = "E_CM_2005.2005";
        public static final String E_CM_2006_2006 = "E_CM_2006.2006";
        public static final String E_CM_2007_2007 = "E_CM_2007.2007";
        public static final String E_CM_2008_2008 = "E_CM_2008.2008";
        public static final String E_CM_2009_2009 = "E_CM_2009.2009";
        public static final String E_CM_3001_3001 = "E_CM_3001.3001";
        public static final String E_CM_3002_3002 = "E_CM_3002.3002";
        public static final String E_CM_3003_3003 = "E_CM_3003.3003";
        public static final String E_CM_3004_3004 = "E_CM_3004.3004";
        public static final String E_CM_3005_3005 = "E_CM_3005.3005";
        public static final String E_CM_3006_3006 = "E_CM_3006.3006";
        public static final String E_CM_3007_3007 = "E_CM_3007.3007";
        public static final String E_CM_3008_3008 = "E_CM_3008.3008";
        public static final String E_CM_3009_3009 = "E_CM_3009.3009";
        public static final String E_CM_3010_3010 = "E_CM_3010.3010";
        public static final String E_CM_3011_3011 = "E_CM_3011.3011";
        public static final String E_CM_3012_3012 = "E_CM_3012.3012";
        public static final String E_CM_3013_3013 = "E_CM_3013.3013";
        public static final String E_CM_3014_3014 = "E_CM_3014.3014";
        public static final String E_CM_3015_3015 = "E_CM_3015.3015";
        public static final String E_CM_3016_3016 = "E_CM_3016.3016";
        public static final String E_CM_3017_3017 = "E_CM_3017.3017";
        public static final String E_CM_3018_3018 = "E_CM_3018.3018";
        public static final String E_CM_3019_3019 = "E_CM_3019.3019";
        public static final String E_CM_3020_3020 = "E_CM_3020.3020";
        public static final String E_CM_3021_3021 = "E_CM_3021.3021";
        public static final String E_CM_3022_3022 = "E_CM_3022.3022";
        public static final String E_CM_3023_3023 = "E_CM_3023.3023";
        public static final String E_CM_3024_3024 = "E_CM_3024.3024";
        public static final String E_CM_3025_3025 = "E_CM_3025.3025";
        public static final String E_CM_3101_3101 = "E_CM_3101.3101";
        public static final String E_CM_3102_3102 = "E_CM_3102.3102";
        public static final String E_CM_3103_3103 = "E_CM_3103.3103";
        public static final String E_CM_3104_3104 = "E_CM_3104.3104";
        public static final String E_CM_3105_3105 = "E_CM_3105.3105";
        public static final String E_CM_8005_8005 = "E_CM_8005.8005";
        public static final String E_CM_8006_8006 = "E_CM_8006.8006";
        public static final String E_CM_8007_8007 = "E_CM_8007.8007";
        public static final String E_CM_8008_8008 = "E_CM_8008.8008";
        public static final String E_CM_8009_8009 = "E_CM_8009.8009";
        public static final String E_CM8010_8010 = "E_CM8010.8010";
        public static final String E_CM8011_8011 = "E_CM8011.8011";
        public static final String E_CM8012_8012 = "E_CM8012.8012";
        public static final String E_CM8013_8013 = "E_CM8013.8013";
        public static final String E_CM_8014_8014 = "E_CM_8014.8014";
        public static final String E_CM8015_8015 = "E_CM_8015.8015";
        public static final String E_CM8016_8016 = "E_CM_8016.8016";
        public static final String E_CM_8017_8017 = "E_CM_8017.8017";
        public static final String DE_CM4003_4003 = "DE_CM4003.4003";
        public static final String DE_CM4006_4006 = "DE_CM4006.4006";
        public static final String DE_CM4007_4007 = "DE_CM4007.4007";
        public static final String DE_CM4008_4008 = "DE_CM4008.4008";
        public static final String DE_CM4009_4009 = "DE_CM4009.4009";
        public static final String DE_CM4010_4010 = "DE_CM4010.4010";
        public static final String DE_CM4011_4011 = "DE_CM4011.4011";
        public static final String DE_CM4012_4012 = "DE_CM4012.4012";
        public static final String DE_CM4013_4013 = "DE_CM4013.4013";
        public static final String DE_CM4014_4014 = "DE_CM4014.4014";
        public static final String DE_CM4015_4015 = "DE_CM4015.4015";
        public static final String DE_CM4016_4016 = "DE_CM4016.4016";
        public static final String DE_CM4017_4017 = "DE_CM4017.4017";
        public static final String DE_CM4018_4018 = "DE_CM4018.4018";
        public static final String DE_CM4019_4019 = "DE_CM4019.4019";
        public static final String DE_CM4020_4020 = "DE_CM4020.4020";
        public static final String DE_CM4021_4021 = "DE_CM4021.4021";
        public static final String DE_CM4022_4022 = "DE_CM4022.4022";
        public static final String DE_CM4023_4023 = "DE_CM4023.4023";
        public static final String DE_CM4024_4024 = "DE_CM4024.4024";
        public static final String DE_CM4025_4025 = "DE_CM4025.4025";
        public static final String DE_CM4026_4026 = "DE_CM4026.4026";
        public static final String DE_CM4027_4027 = "DE_CM4027.4027";
        public static final String DE_CM4028_4028 = "DE_CM4028.4028";
        public static final String DE_CM4029_4029 = "DE_CM4029.4029";
        public static final String DE_CM4030_4030 = "DE_CM4030.4030";
        public static final String DE_CM4032_4032 = "DE_CM4032.4032";
        public static final String DE_CM4033_4033 = "DE_CM4033.4033";
        public static final String DE_CM4034_4034 = "DE_CM4034.4034";
        public static final String DE_CM4035_4035 = "DE_CM4035.4035";
        public static final String DE_CM4036_4036 = "DE_CM4036.4036";
        public static final String DE_CM4039_4039 = "DE_CM4039.4039";
        public static final String DE_CM4040_4040 = "DE_CM4040.4040";
        public static final String TN_CM0000_5001 = "TN_CM0000.5001";
        public static final String TN_CM0000_5002 = "TN_CM0000.5002";
        public static final String TN_CM0000_5005 = "TN_CM0000.5005";
        public static final String TN_CM0000_5006 = "TN_CM0000.5006";
        public static final String TN_CM0000_5007 = "TN_CM0000.5007";
        public static final String TN_CM0000_5008 = "TN_CM0000.5008";
        public static final String TN_CM0000_5009 = "TN_CM0000.5009";
        public static final String TN_CM0000_5010 = "TN_CM0000.5010";
        public static final String TN_CM0000_5011 = "TN_CM0000.5011";
        public static final String TN_CM0000_5012 = "TN_CM0000.5012";
        public static final String TN_CM0000_5013 = "TN_CM0000.5013";
        public static final String TN_CM0000_5014 = "TN_CM0000.5014";
        public static final String TN_CM0000_5015 = "TN_CM0000.5015";
        public static final String TN_CM0000_5016 = "TN_CM0000.5016";
        public static final String TN_CM0000_5017 = "TN_CM0000.5017";
        public static final String TN_CM0000_5018 = "TN_CM0000.5018";
        public static final String TN_CM0000_5019 = "TN_CM0000.5019";
        public static final String TN_CM0000_5020 = "TN_CM0000.5020";
        public static final String TN_CM0000_5021 = "TN_CM0000.5021";
        public static final String TN_CM0000_5022 = "TN_CM0000.5022";
        public static final String TN_CM0000_5023 = "TN_CM0000.5023";
        public static final String TN_CM0000_5024 = "TN_CM0000.5024";
        public static final String TN_CM0000_5025 = "TN_CM0000.5025";
        public static final String TN_CM0000_5026 = "TN_CM0000.5026";
        public static final String TN_CM0000_5027 = "TN_CM0000.5027";
        public static final String TN_CM0000_5028 = "TN_CM0000.5028";
        public static final String TN_CM0000_5029 = "TN_CM0000.5029";
        public static final String TN_CM0000_5030 = "TN_CM0000.5030";
        public static final String TN_CM0000_5031 = "TN_CM0000.5031";
        public static final String TN_CM0000_5032 = "TN_CM0000.5032";
        public static final String TN_CM0000_5033 = "TN_CM0000.5033";
        public static final String TN_CM0000_5034 = "TN_CM0000.5034";
        public static final String TN_CM0000_5035 = "TN_CM0000.5035";
        public static final String TN_CM0000_5036 = "TN_CM0000.5036";
        public static final String TN_CM0000_5037 = "TN_CM0000.5037";
        public static final String TN_CM0000_5038 = "TN_CM0000.5038";
        public static final String E_CM7001_7001 = "E_CM7001.7001";
        public static final String E_CM7002_7002 = "E_CM7002.7002";
        public static final String E_CM7003_7003 = "E_CM7003.7003";
        public static final String E_CM8001_8001 = "E_CM8001.8001";
        public static final String E_CM8002_8002 = "E_CM8002.8002";
        public static final String DE_CM0000_10000 = "DE_CM0000.10000";
        public static final String DE_CM0001_10001 = "DE_CM0001.10001";
        public static final String DE_CM0002_10002 = "DE_CM0002.10002";
        public static final String DE_CM0003_10003 = "DE_CM0003.10003";
        public static final String DE_CM0004_10004 = "DE_CM0004.10004";
        public static final String DE_CM0005_10005 = "DE_CM0005.10005";
        public static final String DE_CM0006_10006 = "DE_CM0006.10006";
        public static final String DE_CM0007_10007 = "DE_CM0007.10007";
        public static final String DE_CM0008_10008 = "DE_CM0008.10008";
        public static final String DE_CM0009_10009 = "DE_CM0009.10009";
        public static final String E_CM20001_20001 = "E_CM20001.20001";
        public static final String E_CM20002_20002 = "E_CM20002.20002";
        public static final String E_CM20003_20003 = "E_CM20003.20003";
        public static final String E_CM20004_20004 = "E_CM20004.20004";
        public static final String E_CM20005_20005 = "E_CM20005.20005";
        public static final String E_CM20006_20006 = "E_CM20006.20006";
        public static final String E_CM22001_22001 = "E_CM22001.22001";
        public static final String E_CM22002_22002 = "E_CM22002.22002";
        public static final String E_CM22003_22003 = "E_CM22003.22003";
        public static final String E_CM22004_22004 = "E_CM22004.22004";
        public static final String E_CM22005_22005 = "E_CM22005.22005";
        public static final String E_CM22006_22006 = "E_CM22006.22006";
        public static final String E_CM22007_22007 = "E_CM22007.22007";
        public static final String E_CM22008_22008 = "E_CM22008.22008";
        public static final String E_CM22009_22009 = "E_CM22009.22009";
        public static final String E_CM22010_22010 = "E_CM22010.22010";
        public static final String E_CM22011_22011 = "E_CM22011.22011";

        public static final String E_CM_0140_1140 = "E_CM_0140.1140";
        public static final String E_CM_0141_1141 = "E_CM_0141.1141";
        public static final String E_CM_0142_1142 = "E_CM_0142.1142";
        public static final String E_CM_0143_1143 = "E_CM_0143.1143";
        public static final String E_CM_0144_1144 = "E_CM_0144.1144";
        public static final String E_CM_0145_1145 = "E_CM_0145.1145";
        public static final String E_CM_0146_1146 = "E_CM_0146.1146";
        public static final String E_CM_0147_1147 = "E_CM_0147.1147";
        public static final String E_CM_0148_1148 = "E_CM_0148.1148";
        public static final String E_CM_0149_1149 = "E_CM_0149.1149";
        public static final String E_CM_0150_1150 = "E_CM_0150.1150";
        public static final String E_CM_0151_1151 = "E_CM_0151.1151";
        public static final String E_CM_0152_1152 = "E_CM_0152.1152";
        public static final String E_CM_0153_1153 = "E_CM_0153.1153";
        public static final String E_CM_0154_1154 = "E_CM_0154.1154";
        public static final String E_CM_0155_1155 = "E_CM_0155.1155";
        public static final String E_CM_0156_1156 = "E_CM_0156.1156";
        public static final String E_CM_0157_1157 = "E_CM_0157.1157";
        public static final String E_CM_0158_1158 = "E_CM_0158.1158";
        public static final String E_CM_0159_1159 = "E_CM_0159.1159";
        public static final String E_CM_0160_1160 = "E_CM_0160.1160";
        public static final String E_CM_0161_1161 = "E_CM_0161.1161";
        public static final String E_CM_0162_1162 = "E_CM_0162.1162";
        public static final String E_CM_0164_1164 = "E_CM_0164.1164";
        public static final String E_CM_0165_1165 = "E_CM_0165.1165";
        public static final String E_CM_0166_1166 = "E_CM_0166.1166";
        public static final String E_CM_0167_1167 = "E_CM_0167.1167";
        public static final String E_CM_0168_1168 = "E_CM_0168.1168";
        public static final String E_CM_0170_1170 = "E_CM_0170.1170";
        public static final String E_CM_0171_1171 = "E_CM_0171.1171";
        public static final String E_CM_0172_1172 = "E_CM_0172.1172";
        public static final String E_CM_0173_1173 = "E_CM_0173.1173";
        public static final String E_CM_0174_1174 = "E_CM_0174.1174";
        public static final String E_CM_0175_1175 = "E_CM_0175.1175";
        public static final String E_CM_0176_1176 = "E_CM_0176.1176";
        public static final String E_CM_0177_1177 = "E_CM_0177.1177";
        public static final String E_CM_0178_1178 = "E_CM_0178.1178";
        public static final String E_CM_0179_1179 = "E_CM_0179.1179";
        public static final String E_CM_0180_1180 = "E_CM_0180.1180";
        public static final String E_CM_0181_1181 = "E_CM_0181.1181";
        public static final String E_CM_0182_1182 = "E_CM_0182.1182";
    }

    public final class TABID {
        private TABID() {
        }
         public static final String NEW_CREATE_TAB = "New_Create_Tab_Name";
         public static final String MODIFY_TAB = "Modify_Tab_Name";
         public static final String COPY_TAB = "Copy_Tab_Name";
         public static final String DELETE_TAB = "Delete_Tab_Name";
         public static final String REGISTER_MODIFY_TAB = "Register_Modify_Tab_Name";
         public static final String ASSUM_FOOD_TAB = "Assum_Food_Tab_Name";
         public static final String NEW_FOOD_TAB = "New_Food_Tab_Name";
         public static final String DELETECANCEL_TAB = "DeleteCancel_Tab_Name";
         public static final String ADD_PRODUCT_TAB = "Add_Product_Tab_Name";
         public static final String NEXT_TERM_TAB = "Next_Term_Tab_Name";
         public static final String NEW_NEXT_TAB = "New_Next_Term_Tab_Name";
    }
}
