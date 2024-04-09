package demo.mo.util;

/**
 * 【StringCheck基本クラス】<BR>
 * 入力禁止文字列１のリソースクラス<BR>
 * リソースクラスのみで構成されたクラス<BR>
 *
 */

// [履歴]
// 入力許容コード一覧より~(0x007e)を削除。
// 入力許容コード一覧より~(0x002c)を削除。
// ・変数MILLIGRAM_CODEを追加
final class StringCheckCode1 {
    private StringCheckCode1() {
    }
    // チェックコードの配列は、入力を許容する文字のコードがコードの昇順で登録されているデータです。
    public static final Character[] CODE =
        {0x000a, 0x000d, 0x0020, 0x0021, 0x0022, 0x0023, 0x0024, 0x0025, 0x0026, 0x0027,
            0x0028, 0x0029, 0x002a, 0x002b, /* 0x002c, */0x002d, 0x002e, 0x002f, 0x0030, 0x0031, 0x0032, 0x0033, 0x0034,
            0x0035, 0x0036, 0x0037, 0x0038, 0x0039, 0x003a, 0x003b, 0x003c, 0x003d, 0x003e, 0x003f, 0x0040, 0x0041,
            0x0042, 0x0043, 0x0044, 0x0045, 0x0046, 0x0047, 0x0048, 0x0049, 0x004a, 0x004b, 0x004c, 0x004d, 0x004e,
            0x004f, 0x0050, 0x0051, 0x0052, 0x0053, 0x0054, 0x0055, 0x0056, 0x0057, 0x0058, 0x0059, 0x005a, 0x005b,
            0x005c, 0x005d, 0x005e, 0x005f, 0x0060, 0x0061, 0x0062, 0x0063, 0x0064, 0x0065, 0x0066, 0x0067, 0x0068,
            0x0069, 0x006a, 0x006b, 0x006c, 0x006d, 0x006e, 0x006f, 0x0070, 0x0071, 0x0072, 0x0073, 0x0074, 0x0075,
            0x0076, 0x0077, 0x0078, 0x0079, 0x007a, 0x007b, 0x007c, 0x007d, /* 0x007e, */0x00a7, 0x00a8, 0x00b0, 0x00b1,
            0x00b4, 0x00b6, 0x00d7, 0x00f7, 0x0391, 0x0392, 0x0393, 0x0394, 0x0395, 0x0396, 0x0397, 0x0398, 0x0399,
            0x039a, 0x039b, 0x039c, 0x039d, 0x039e, 0x039f, 0x03a0, 0x03a1, 0x03a3, 0x03a4, 0x03a5, 0x03a6, 0x03a7,
            0x03a8, 0x03a9, 0x03b1, 0x03b2, 0x03b3, 0x03b4, 0x03b5, 0x03b6, 0x03b7, 0x03b8, 0x03b9, 0x03ba, 0x03bb,
            0x03bc, 0x03bd, 0x03be, 0x03bf, 0x03c0, 0x03c1, 0x03c3, 0x03c4, 0x03c5, 0x03c6, 0x03c7, 0x03c8, 0x03c9,
            0x0401, 0x0410, 0x0411, 0x0412, 0x0413, 0x0414, 0x0415, 0x0416, 0x0417, 0x0418, 0x0419, 0x041a, 0x041b,
            0x041c, 0x041d, 0x041e, 0x041f, 0x0420, 0x0421, 0x0422, 0x0423, 0x0424, 0x0425, 0x0426, 0x0427, 0x0428,
            0x0429, 0x042a, 0x042b, 0x042c, 0x042d, 0x042e, 0x042f, 0x0430, 0x0431, 0x0432, 0x0433, 0x0434, 0x0435,
            0x0436, 0x0437, 0x0438, 0x0439, 0x043a, 0x043b, 0x043c, 0x043d, 0x043e, 0x043f, 0x0440, 0x0441, 0x0442,
            0x0443, 0x0444, 0x0445, 0x0446, 0x0447, 0x0448, 0x0449, 0x044a, 0x044b, 0x044c, 0x044d, 0x044e, 0x044f,
            0x0451, 0x2010, 0x2015, 0x2018, 0x2019, 0x201c, 0x201d, 0x2020, 0x2021, 0x2025, 0x2026, 0x2030, 0x2032,
            0x2033, 0x203b, 0x2103, 0x212b, 0x2190, 0x2191, 0x2192, 0x2193, 0x21d2, 0x21d4, 0x2200, 0x2202, 0x2203,
            0x2207, 0x2208, 0x220b, 0x221a, 0x221d, 0x221e, 0x2220, 0x2225, 0x2227, 0x2228, 0x2229, 0x222a, 0x222b,
            0x222c, 0x2234, 0x2235, 0x223d, 0x2252, 0x2260, 0x2261, 0x2266, 0x2267, 0x226a, 0x226b, 0x2282, 0x2283,
            0x2286, 0x2287, 0x22a5, 0x2312, 0x2500, 0x2501, 0x2502, 0x2503, 0x250c, 0x250f, 0x2510, 0x2513, 0x2514,
            0x2517, 0x2518, 0x251b, 0x251c, 0x251d, 0x2520, 0x2523, 0x2524, 0x2525, 0x2528, 0x252b, 0x252c, 0x252f,
            0x2530, 0x2533, 0x2534, 0x2537, 0x2538, 0x253b, 0x253c, 0x253f, 0x2542, 0x254b, 0x25a0, 0x25a1, 0x25b2,
            0x25b3, 0x25bc, 0x25bd, 0x25c6, 0x25c7, 0x25cb, 0x25ce, 0x25cf, 0x25ef, 0x2605, 0x2606, 0x2640, 0x2642,
            0x266a, 0x266d, 0x266f, 0x3000, 0x3001, 0x3002, 0x3003, 0x3005, 0x3006, 0x3007, 0x3008, 0x3009, 0x300a,
            0x300b, 0x300c, 0x300d, 0x300e, 0x300f, 0x3010, 0x3011, 0x3012, 0x3013, 0x3014, 0x3015, 0x3041, 0x3042,
            0x3043, 0x3044, 0x3045, 0x3046, 0x3047, 0x3048, 0x3049, 0x304a, 0x304b, 0x304c, 0x304d, 0x304e, 0x304f,
            0x3050, 0x3051, 0x3052, 0x3053, 0x3054, 0x3055, 0x3056, 0x3057, 0x3058, 0x3059, 0x305a, 0x305b, 0x305c,
            0x305d, 0x305e, 0x305f, 0x3060, 0x3061, 0x3062, 0x3063, 0x3064, 0x3065, 0x3066, 0x3067, 0x3068, 0x3069,
            0x306a, 0x306b, 0x306c, 0x306d, 0x306e, 0x306f, 0x3070, 0x3071, 0x3072, 0x3073, 0x3074, 0x3075, 0x3076,
            0x3077, 0x3078, 0x3079, 0x307a, 0x307b, 0x307c, 0x307d, 0x307e, 0x307f, 0x3080, 0x3081, 0x3082, 0x3083,
            0x3084, 0x3085, 0x3086, 0x3087, 0x3088, 0x3089, 0x308a, 0x308b, 0x308c, 0x308d, 0x308e, 0x308f, 0x3090,
            0x3091, 0x3092, 0x3093, 0x309b, 0x309c, 0x309d, 0x309e, 0x30a1, 0x30a2, 0x30a3, 0x30a4, 0x30a5, 0x30a6,
            0x30a7, 0x30a8, 0x30a9, 0x30aa, 0x30ab, 0x30ac, 0x30ad, 0x30ae, 0x30af, 0x30b0, 0x30b1, 0x30b2, 0x30b3,
            0x30b4, 0x30b5, 0x30b6, 0x30b7, 0x30b8, 0x30b9, 0x30ba, 0x30bb, 0x30bc, 0x30bd, 0x30be, 0x30bf, 0x30c0,
            0x30c1, 0x30c2, 0x30c3, 0x30c4, 0x30c5, 0x30c6, 0x30c7, 0x30c8, 0x30c9, 0x30ca, 0x30cb, 0x30cc, 0x30cd,
            0x30ce, 0x30cf, 0x30d0, 0x30d1, 0x30d2, 0x30d3, 0x30d4, 0x30d5, 0x30d6, 0x30d7, 0x30d8, 0x30d9, 0x30da,
            0x30db, 0x30dc, 0x30dd, 0x30de, 0x30df, 0x30e0, 0x30e1, 0x30e2, 0x30e3, 0x30e4, 0x30e5, 0x30e6, 0x30e7,
            0x30e8, 0x30e9, 0x30ea, 0x30eb, 0x30ec, 0x30ed, 0x30ee, 0x30ef, 0x30f0, 0x30f1, 0x30f2, 0x30f3, 0x30f4,
            0x30f5, 0x30f6, 0x30fb, 0x30fc, 0x30fd, 0x30fe, 0x4e00, 0x4e01, 0x4e03, 0x4e07, 0x4e08, 0x4e09, 0x4e0a,
            0x4e0b, 0x4e0d, 0x4e0e, 0x4e10, 0x4e11, 0x4e14, 0x4e15, 0x4e16, 0x4e17, 0x4e18, 0x4e19, 0x4e1e, 0x4e21,
            0x4e26, 0x4e2a, 0x4e2d, 0x4e31, 0x4e32, 0x4e36, 0x4e38, 0x4e39, 0x4e3b, 0x4e3c, 0x4e3f, 0x4e42, 0x4e43,
            0x4e45, 0x4e4b, 0x4e4d, 0x4e4e, 0x4e4f, 0x4e55, 0x4e56, 0x4e57, 0x4e58, 0x4e59, 0x4e5d, 0x4e5e, 0x4e5f,
            0x4e62, 0x4e71, 0x4e73, 0x4e7e, 0x4e80, 0x4e82, 0x4e85, 0x4e86, 0x4e88, 0x4e89, 0x4e8a, 0x4e8b, 0x4e8c,
            0x4e8e, 0x4e91, 0x4e92, 0x4e94, 0x4e95, 0x4e98, 0x4e99, 0x4e9b, 0x4e9c, 0x4e9e, 0x4e9f, 0x4ea0, 0x4ea1,
            0x4ea2, 0x4ea4, 0x4ea5, 0x4ea6, 0x4ea8, 0x4eab, 0x4eac, 0x4ead, 0x4eae, 0x4eb0, 0x4eb3, 0x4eb6, 0x4eba,
            0x4ec0, 0x4ec1, 0x4ec2, 0x4ec4, 0x4ec6, 0x4ec7, 0x4eca, 0x4ecb, 0x4ecd, 0x4ece, 0x4ecf, 0x4ed4, 0x4ed5,
            0x4ed6, 0x4ed7, 0x4ed8, 0x4ed9, 0x4edd, 0x4ede, 0x4edf, 0x4ee3, 0x4ee4, 0x4ee5, 0x4eed, 0x4eee, 0x4ef0,
            0x4ef2, 0x4ef6, 0x4ef7, 0x4efb, 0x4f01, 0x4f09, 0x4f0a, 0x4f0d, 0x4f0e, 0x4f0f, 0x4f10, 0x4f11, 0x4f1a,
            0x4f1c, 0x4f1d, 0x4f2f, 0x4f30, 0x4f34, 0x4f36, 0x4f38, 0x4f3a, 0x4f3c, 0x4f3d, 0x4f43, 0x4f46, 0x4f47,
            0x4f4d, 0x4f4e, 0x4f4f, 0x4f50, 0x4f51, 0x4f53, 0x4f55, 0x4f57, 0x4f59, 0x4f5a, 0x4f5b, 0x4f5c, 0x4f5d,
            0x4f5e, 0x4f69, 0x4f6f, 0x4f70, 0x4f73, 0x4f75, 0x4f76, 0x4f7b, 0x4f7c, 0x4f7f, 0x4f83, 0x4f86, 0x4f88,
            0x4f8b, 0x4f8d, 0x4f8f, 0x4f91, 0x4f96, 0x4f98, 0x4f9b, 0x4f9d, 0x4fa0, 0x4fa1, 0x4fab, 0x4fad, 0x4fae,
            0x4faf, 0x4fb5, 0x4fb6, 0x4fbf, 0x4fc2, 0x4fc3, 0x4fc4, 0x4fca, 0x4fce, 0x4fd0, 0x4fd1, 0x4fd4, 0x4fd7,
            0x4fd8, 0x4fda, 0x4fdb, 0x4fdd, 0x4fdf, 0x4fe1, 0x4fe3, 0x4fe4, 0x4fe5, 0x4fee, 0x4fef, 0x4ff3, 0x4ff5,
            0x4ff6, 0x4ff8, 0x4ffa, 0x4ffe, 0x5005, 0x5006, 0x5009, 0x500b, 0x500d, 0x500f, 0x5011, 0x5012, 0x5014,
            0x5016, 0x5019, 0x501a, 0x501f, 0x5021, 0x5023, 0x5024, 0x5025, 0x5026, 0x5028, 0x5029, 0x502a, 0x502b,
            0x502c, 0x502d, 0x5036, 0x5039, 0x5043, 0x5047, 0x5048, 0x5049, 0x504f, 0x5050, 0x5055, 0x5056, 0x505a,
            0x505c, 0x5065, 0x506c, 0x5072, 0x5074, 0x5075, 0x5076, 0x5078, 0x507d, 0x5080, 0x5085, 0x508d, 0x5091,
            0x5098, 0x5099, 0x509a, 0x50ac, 0x50ad, 0x50b2, 0x50b3, 0x50b4, 0x50b5, 0x50b7, 0x50be, 0x50c2, 0x50c5,
            0x50c9, 0x50ca, 0x50cd, 0x50cf, 0x50d1, 0x50d5, 0x50d6, 0x50da, 0x50de, 0x50e3, 0x50e5, 0x50e7, 0x50ed,
            0x50ee, 0x50f5, 0x50f9, 0x50fb, 0x5100, 0x5101, 0x5102, 0x5104, 0x5109, 0x5112, 0x5114, 0x5115, 0x5116,
            0x5118, 0x511a, 0x511f, 0x5121, 0x512a, 0x5132, 0x5137, 0x513a, 0x513b, 0x513c, 0x513f, 0x5140, 0x5141,
            0x5143, 0x5144, 0x5145, 0x5146, 0x5147, 0x5148, 0x5149, 0x514b, 0x514c, 0x514d, 0x514e, 0x5150, 0x5152,
            0x5154, 0x515a, 0x515c, 0x5162, 0x5165, 0x5168, 0x5169, 0x516a, 0x516b, 0x516c, 0x516d, 0x516e, 0x5171,
            0x5175, 0x5176, 0x5177, 0x5178, 0x517c, 0x5180, 0x5182, 0x5185, 0x5186, 0x5189, 0x518a, 0x518c, 0x518d,
            0x518f, 0x5190, 0x5191, 0x5192, 0x5193, 0x5195, 0x5196, 0x5197, 0x5199, 0x51a0, 0x51a2, 0x51a4, 0x51a5,
            0x51a6, 0x51a8, 0x51a9, 0x51aa, 0x51ab, 0x51ac, 0x51b0, 0x51b1, 0x51b2, 0x51b3, 0x51b4, 0x51b5, 0x51b6,
            0x51b7, 0x51bd, 0x51c4, 0x51c5, 0x51c6, 0x51c9, 0x51cb, 0x51cc, 0x51cd, 0x51d6, 0x51db, 0x51dc, 0x51dd,
            0x51e0, 0x51e1, 0x51e6, 0x51e7, 0x51e9, 0x51ea, 0x51ed, 0x51f0, 0x51f1, 0x51f5, 0x51f6, 0x51f8, 0x51f9,
            0x51fa, 0x51fd, 0x51fe, 0x5200, 0x5203, 0x5204, 0x5206, 0x5207, 0x5208, 0x520a, 0x520b, 0x520e, 0x5211,
            0x5214, 0x5217, 0x521d, 0x5224, 0x5225, 0x5227, 0x5229, 0x522a, 0x522e, 0x5230, 0x5233, 0x5236, 0x5237,
            0x5238, 0x5239, 0x523a, 0x523b, 0x5243, 0x5244, 0x5247, 0x524a, 0x524b, 0x524c, 0x524d, 0x524f, 0x5254,
            0x5256, 0x525b, 0x525e, 0x5263, 0x5264, 0x5265, 0x5269, 0x526a, 0x526f, 0x5270, 0x5271, 0x5272, 0x5273,
            0x5274, 0x5275, 0x527d, 0x527f, 0x5283, 0x5287, 0x5288, 0x5289, 0x528d, 0x5291, 0x5292, 0x5294, 0x529b,
            0x529f, 0x52a0, 0x52a3, 0x52a9, 0x52aa, 0x52ab, 0x52ac, 0x52ad, 0x52b1, 0x52b4, 0x52b5, 0x52b9, 0x52bc,
            0x52be, 0x52c1, 0x52c3, 0x52c5, 0x52c7, 0x52c9, 0x52cd, 0x52d2, 0x52d5, 0x52d7, 0x52d8, 0x52d9, 0x52dd,
            0x52de, 0x52df, 0x52e0, 0x52e2, 0x52e3, 0x52e4, 0x52e6, 0x52e7, 0x52f2, 0x52f3, 0x52f5, 0x52f8, 0x52f9,
            0x52fa, 0x52fe, 0x52ff, 0x5301, 0x5302, 0x5305, 0x5306, 0x5308, 0x530d, 0x530f, 0x5310, 0x5315, 0x5316,
            0x5317, 0x5319, 0x531a, 0x531d, 0x5320, 0x5321, 0x5323, 0x532a, 0x532f, 0x5331, 0x5333, 0x5338, 0x5339,
            0x533a, 0x533b, 0x533f, 0x5340, 0x5341, 0x5343, 0x5345, 0x5346, 0x5347, 0x5348, 0x5349, 0x534a, 0x534d,
            0x5351, 0x5352, 0x5353, 0x5354, 0x5357, 0x5358, 0x535a, 0x535c, 0x535e, 0x5360, 0x5366, 0x5369, 0x536e,
            0x536f, 0x5370, 0x5371, 0x5373, 0x5374, 0x5375, 0x5377, 0x5378, 0x537b, 0x537f, 0x5382, 0x5384, 0x5396,
            0x5398, 0x539a, 0x539f, 0x53a0, 0x53a5, 0x53a6, 0x53a8, 0x53a9, 0x53ad, 0x53ae, 0x53b0, 0x53b3, 0x53b6,
            0x53bb, 0x53c2, 0x53c3, 0x53c8, 0x53c9, 0x53ca, 0x53cb, 0x53cc, 0x53cd, 0x53ce, 0x53d4, 0x53d6, 0x53d7,
            0x53d9, 0x53db, 0x53df, 0x53e1, 0x53e2, 0x53e3, 0x53e4, 0x53e5, 0x53e8, 0x53e9, 0x53ea, 0x53eb, 0x53ec,
            0x53ed, 0x53ee, 0x53ef, 0x53f0, 0x53f1, 0x53f2, 0x53f3, 0x53f6, 0x53f7, 0x53f8, 0x53fa, 0x5401, 0x5403,
            0x5404, 0x5408, 0x5409, 0x540a, 0x540b, 0x540c, 0x540d, 0x540e, 0x540f, 0x5410, 0x5411, 0x541b, 0x541d,
            0x541f, 0x5420, 0x5426, 0x5429, 0x542b, 0x542c, 0x542d, 0x542e, 0x5436, 0x5438, 0x5439, 0x543b, 0x543c,
            0x543d, 0x543e, 0x5440, 0x5442, 0x5446, 0x5448, 0x5449, 0x544a, 0x544e, 0x5451, 0x545f, 0x5468, 0x546a,
            0x5470, 0x5471, 0x5473, 0x5475, 0x5476, 0x5477, 0x547b, 0x547c, 0x547d, 0x5480, 0x5484, 0x5486, 0x548b,
            0x548c, 0x548e, 0x548f, 0x5490, 0x5492, 0x54a2, 0x54a4, 0x54a5, 0x54a8, 0x54ab, 0x54ac, 0x54af, 0x54b2,
            0x54b3, 0x54b8, 0x54bc, 0x54bd, 0x54be, 0x54c0, 0x54c1, 0x54c2, 0x54c4, 0x54c7, 0x54c8, 0x54c9, 0x54d8,
            0x54e1, 0x54e2, 0x54e5, 0x54e6, 0x54e8, 0x54e9, 0x54ed, 0x54ee, 0x54f2, 0x54fa, 0x54fd, 0x5504, 0x5506,
            0x5507, 0x550f, 0x5510, 0x5514, 0x5516, 0x552e, 0x552f, 0x5531, 0x5533, 0x5538, 0x5539, 0x553e, 0x5540,
            0x5544, 0x5545, 0x5546, 0x554c, 0x554f, 0x5553, 0x5556, 0x5557, 0x555c, 0x555d, 0x5563, 0x557b, 0x557c,
            0x557e, 0x5580, 0x5583, 0x5584, 0x5587, 0x5589, 0x558a, 0x558b, 0x5598, 0x5599, 0x559a, 0x559c, 0x559d,
            0x559e, 0x559f, 0x55a7, 0x55a8, 0x55a9, 0x55aa, 0x55ab, 0x55ac, 0x55ae, 0x55b0, 0x55b6, 0x55c4, 0x55c5,
            0x55c7, 0x55d4, 0x55da, 0x55dc, 0x55df, 0x55e3, 0x55e4, 0x55f7, 0x55f9, 0x55fd, 0x55fe, 0x5606, 0x5609,
            0x5614, 0x5616, 0x5617, 0x5618, 0x561b, 0x5629, 0x562f, 0x5631, 0x5632, 0x5634, 0x5636, 0x5638, 0x5642,
            0x564c, 0x564e, 0x5650, 0x565b, 0x5664, 0x5668, 0x566a, 0x566b, 0x566c, 0x5674, 0x5678, 0x567a, 0x5680,
            0x5686, 0x5687, 0x568a, 0x568f, 0x5694, 0x56a0, 0x56a2, 0x56a5, 0x56ae, 0x56b4, 0x56b6, 0x56bc, 0x56c0,
            0x56c1, 0x56c2, 0x56c3, 0x56c8, 0x56ce, 0x56d1, 0x56d3, 0x56d7, 0x56d8, 0x56da, 0x56db, 0x56de, 0x56e0,
            0x56e3, 0x56ee, 0x56f0, 0x56f2, 0x56f3, 0x56f9, 0x56fa, 0x56fd, 0x56ff, 0x5700, 0x5703, 0x5704, 0x5708,
            0x5709, 0x570b, 0x570d, 0x570f, 0x5712, 0x5713, 0x5716, 0x5718, 0x571c, 0x571f, 0x5726, 0x5727, 0x5728,
            0x572d, 0x5730, 0x5737, 0x5738, 0x573b, 0x5740, 0x5742, 0x5747, 0x574a, 0x574e, 0x574f, 0x5750, 0x5751,
            0x5761, 0x5764, 0x5766, 0x5769, 0x576a, 0x577f, 0x5782, 0x5788, 0x5789, 0x578b, 0x5793, 0x57a0, 0x57a2,
            0x57a3, 0x57a4, 0x57aa, 0x57b0, 0x57b3, 0x57c0, 0x57c3, 0x57c6, 0x57cb, 0x57ce, 0x57d2, 0x57d3, 0x57d4,
            0x57d6, 0x57dc, 0x57df, 0x57e0, 0x57e3, 0x57f4, 0x57f7, 0x57f9, 0x57fa, 0x57fc, 0x5800, 0x5802, 0x5805,
            0x5806, 0x580a, 0x580b, 0x5815, 0x5819, 0x581d, 0x5821, 0x5824, 0x582a, 0x582f, 0x5830, 0x5831, 0x5834,
            0x5835, 0x583a, 0x583d, 0x5840, 0x5841, 0x584a, 0x584b, 0x5851, 0x5852, 0x5854, 0x5857, 0x5858, 0x5859,
            0x585a, 0x585e, 0x5862, 0x5869, 0x586b, 0x5870, 0x5872, 0x5875, 0x5879, 0x587e, 0x5883, 0x5885, 0x5893,
            0x5897, 0x589c, 0x589f, 0x58a8, 0x58ab, 0x58ae, 0x58b3, 0x58b8, 0x58b9, 0x58ba, 0x58bb, 0x58be, 0x58c1,
            0x58c5, 0x58c7, 0x58ca, 0x58cc, 0x58d1, 0x58d3, 0x58d5, 0x58d7, 0x58d8, 0x58d9, 0x58dc, 0x58de, 0x58df,
            0x58e4, 0x58e5, 0x58eb, 0x58ec, 0x58ee, 0x58ef, 0x58f0, 0x58f1, 0x58f2, 0x58f7, 0x58f9, 0x58fa, 0x58fb,
            0x58fc, 0x58fd, 0x5902, 0x5909, 0x590a, 0x590f, 0x5910, 0x5915, 0x5916, 0x5918, 0x5919, 0x591a, 0x591b,
            0x591c, 0x5922, 0x5925, 0x5927, 0x5929, 0x592a, 0x592b, 0x592c, 0x592d, 0x592e, 0x5931, 0x5932, 0x5937,
            0x5938, 0x593e, 0x5944, 0x5947, 0x5948, 0x5949, 0x594e, 0x594f, 0x5950, 0x5951, 0x5954, 0x5955, 0x5957,
            0x5958, 0x595a, 0x5960, 0x5962, 0x5965, 0x5967, 0x5968, 0x5969, 0x596a, 0x596c, 0x596e, 0x5973, 0x5974,
            0x5978, 0x597d, 0x5981, 0x5982, 0x5983, 0x5984, 0x598a, 0x598d, 0x5993, 0x5996, 0x5999, 0x599b, 0x599d,
            0x59a3, 0x59a5, 0x59a8, 0x59ac, 0x59b2, 0x59b9, 0x59bb, 0x59be, 0x59c6, 0x59c9, 0x59cb, 0x59d0, 0x59d1,
            0x59d3, 0x59d4, 0x59d9, 0x59da, 0x59dc, 0x59e5, 0x59e6, 0x59e8, 0x59ea, 0x59eb, 0x59f6, 0x59fb, 0x59ff,
            0x5a01, 0x5a03, 0x5a09, 0x5a11, 0x5a18, 0x5a1a, 0x5a1c, 0x5a1f, 0x5a20, 0x5a25, 0x5a29, 0x5a2f, 0x5a35,
            0x5a36, 0x5a3c, 0x5a40, 0x5a41, 0x5a46, 0x5a49, 0x5a5a, 0x5a62, 0x5a66, 0x5a6a, 0x5a6c, 0x5a7f, 0x5a92,
            0x5a9a, 0x5a9b, 0x5abc, 0x5abd, 0x5abe, 0x5ac1, 0x5ac2, 0x5ac9, 0x5acb, 0x5acc, 0x5ad0, 0x5ad6, 0x5ad7,
            0x5ae1, 0x5ae3, 0x5ae6, 0x5ae9, 0x5afa, 0x5afb, 0x5b09, 0x5b0b, 0x5b0c, 0x5b16, 0x5b22, 0x5b2a, 0x5b2c,
            0x5b30, 0x5b32, 0x5b36, 0x5b3e, 0x5b40, 0x5b43, 0x5b45, 0x5b50, 0x5b51, 0x5b54, 0x5b55, 0x5b57, 0x5b58,
            0x5b5a, 0x5b5b, 0x5b5c, 0x5b5d, 0x5b5f, 0x5b63, 0x5b64, 0x5b65, 0x5b66, 0x5b69, 0x5b6b, 0x5b70, 0x5b71,
            0x5b73, 0x5b75, 0x5b78, 0x5b7a, 0x5b80, 0x5b83, 0x5b85, 0x5b87, 0x5b88, 0x5b89, 0x5b8b, 0x5b8c, 0x5b8d,
            0x5b8f, 0x5b95, 0x5b97, 0x5b98, 0x5b99, 0x5b9a, 0x5b9b, 0x5b9c, 0x5b9d, 0x5b9f, 0x5ba2, 0x5ba3, 0x5ba4,
            0x5ba5, 0x5ba6, 0x5bae, 0x5bb0, 0x5bb3, 0x5bb4, 0x5bb5, 0x5bb6, 0x5bb8, 0x5bb9, 0x5bbf, 0x5bc2, 0x5bc3,
            0x5bc4, 0x5bc5, 0x5bc6, 0x5bc7, 0x5bc9, 0x5bcc, 0x5bd0, 0x5bd2, 0x5bd3, 0x5bd4, 0x5bdb, 0x5bdd, 0x5bde,
            0x5bdf, 0x5be1, 0x5be2, 0x5be4, 0x5be5, 0x5be6, 0x5be7, 0x5be8, 0x5be9, 0x5beb, 0x5bee, 0x5bf0, 0x5bf3,
            0x5bf5, 0x5bf6, 0x5bf8, 0x5bfa, 0x5bfe, 0x5bff, 0x5c01, 0x5c02, 0x5c04, 0x5c05, 0x5c06, 0x5c07, 0x5c08,
            0x5c09, 0x5c0a, 0x5c0b, 0x5c0d, 0x5c0e, 0x5c0f, 0x5c11, 0x5c13, 0x5c16, 0x5c1a, 0x5c20, 0x5c22, 0x5c24,
            0x5c28, 0x5c2d, 0x5c31, 0x5c38, 0x5c39, 0x5c3a, 0x5c3b, 0x5c3c, 0x5c3d, 0x5c3e, 0x5c3f, 0x5c40, 0x5c41,
            0x5c45, 0x5c46, 0x5c48, 0x5c4a, 0x5c4b, 0x5c4d, 0x5c4e, 0x5c4f, 0x5c50, 0x5c51, 0x5c53, 0x5c55, 0x5c5e,
            0x5c60, 0x5c61, 0x5c64, 0x5c65, 0x5c6c, 0x5c6e, 0x5c6f, 0x5c71, 0x5c76, 0x5c79, 0x5c8c, 0x5c90, 0x5c91,
            0x5c94, 0x5ca1, 0x5ca8, 0x5ca9, 0x5cab, 0x5cac, 0x5cb1, 0x5cb3, 0x5cb6, 0x5cb7, 0x5cb8, 0x5cbb, 0x5cbc,
            0x5cbe, 0x5cc5, 0x5cc7, 0x5cd9, 0x5ce0, 0x5ce1, 0x5ce8, 0x5ce9, 0x5cea, 0x5ced, 0x5cef, 0x5cf0, 0x5cf6,
            0x5cfa, 0x5cfb, 0x5cfd, 0x5d07, 0x5d0b, 0x5d0e, 0x5d11, 0x5d14, 0x5d15, 0x5d16, 0x5d17, 0x5d18, 0x5d19,
            0x5d1a, 0x5d1b, 0x5d1f, 0x5d22, 0x5d29, 0x5d4b, 0x5d4c, 0x5d4e, 0x5d50, 0x5d52, 0x5d5c, 0x5d69, 0x5d6c,
            0x5d6f, 0x5d73, 0x5d76, 0x5d82, 0x5d84, 0x5d87, 0x5d8b, 0x5d8c, 0x5d90, 0x5d9d, 0x5da2, 0x5dac, 0x5dae,
            0x5db7, 0x5dba, 0x5dbc, 0x5dbd, 0x5dc9, 0x5dcc, 0x5dcd, 0x5dd2, 0x5dd3, 0x5dd6, 0x5ddb, 0x5ddd, 0x5dde,
            0x5de1, 0x5de3, 0x5de5, 0x5de6, 0x5de7, 0x5de8, 0x5deb, 0x5dee, 0x5df1, 0x5df2, 0x5df3, 0x5df4, 0x5df5,
            0x5df7, 0x5dfb, 0x5dfd, 0x5dfe, 0x5e02, 0x5e03, 0x5e06, 0x5e0b, 0x5e0c, 0x5e11, 0x5e16, 0x5e19, 0x5e1a,
            0x5e1b, 0x5e1d, 0x5e25, 0x5e2b, 0x5e2d, 0x5e2f, 0x5e30, 0x5e33, 0x5e36, 0x5e37, 0x5e38, 0x5e3d, 0x5e40,
            0x5e43, 0x5e44, 0x5e45, 0x5e47, 0x5e4c, 0x5e4e, 0x5e54, 0x5e55, 0x5e57, 0x5e5f, 0x5e61, 0x5e62, 0x5e63,
            0x5e64, 0x5e72, 0x5e73, 0x5e74, 0x5e75, 0x5e76, 0x5e78, 0x5e79, 0x5e7a, 0x5e7b, 0x5e7c, 0x5e7d, 0x5e7e,
            0x5e7f, 0x5e81, 0x5e83, 0x5e84, 0x5e87, 0x5e8a, 0x5e8f, 0x5e95, 0x5e96, 0x5e97, 0x5e9a, 0x5e9c, 0x5ea0,
            0x5ea6, 0x5ea7, 0x5eab, 0x5ead, 0x5eb5, 0x5eb6, 0x5eb7, 0x5eb8, 0x5ec1, 0x5ec2, 0x5ec3, 0x5ec8, 0x5ec9,
            0x5eca, 0x5ecf, 0x5ed0, 0x5ed3, 0x5ed6, 0x5eda, 0x5edb, 0x5edd, 0x5edf, 0x5ee0, 0x5ee1, 0x5ee2, 0x5ee3,
            0x5ee8, 0x5ee9, 0x5eec, 0x5ef0, 0x5ef1, 0x5ef3, 0x5ef4, 0x5ef6, 0x5ef7, 0x5ef8, 0x5efa, 0x5efb, 0x5efc,
            0x5efe, 0x5eff, 0x5f01, 0x5f03, 0x5f04, 0x5f09, 0x5f0a, 0x5f0b, 0x5f0c, 0x5f0d, 0x5f0f, 0x5f10, 0x5f11,
            0x5f13, 0x5f14, 0x5f15, 0x5f16, 0x5f17, 0x5f18, 0x5f1b, 0x5f1f, 0x5f25, 0x5f26, 0x5f27, 0x5f29, 0x5f2d,
            0x5f2f, 0x5f31, 0x5f35, 0x5f37, 0x5f38, 0x5f3c, 0x5f3e, 0x5f41, 0x5f48, 0x5f4a, 0x5f4c, 0x5f4e, 0x5f51,
            0x5f53, 0x5f56, 0x5f57, 0x5f59, 0x5f5c, 0x5f5d, 0x5f61, 0x5f62, 0x5f66, 0x5f69, 0x5f6a, 0x5f6b, 0x5f6c,
            0x5f6d, 0x5f70, 0x5f71, 0x5f73, 0x5f77, 0x5f79, 0x5f7c, 0x5f7f, 0x5f80, 0x5f81, 0x5f82, 0x5f83, 0x5f84,
            0x5f85, 0x5f87, 0x5f88, 0x5f8a, 0x5f8b, 0x5f8c, 0x5f90, 0x5f91, 0x5f92, 0x5f93, 0x5f97, 0x5f98, 0x5f99,
            0x5f9e, 0x5fa0, 0x5fa1, 0x5fa8, 0x5fa9, 0x5faa, 0x5fad, 0x5fae, 0x5fb3, 0x5fb4, 0x5fb9, 0x5fbc, 0x5fbd,
            0x5fc3, 0x5fc5, 0x5fcc, 0x5fcd, 0x5fd6, 0x5fd7, 0x5fd8, 0x5fd9, 0x5fdc, 0x5fdd, 0x5fe0, 0x5fe4, 0x5feb,
            0x5ff0, 0x5ff1, 0x5ff5, 0x5ff8, 0x5ffb, 0x5ffd, 0x5fff, 0x600e, 0x600f, 0x6010, 0x6012, 0x6015, 0x6016,
            0x6019, 0x601b, 0x601c, 0x601d, 0x6020, 0x6021, 0x6025, 0x6026, 0x6027, 0x6028, 0x6029, 0x602a, 0x602b,
            0x602f, 0x6031, 0x603a, 0x6041, 0x6042, 0x6043, 0x6046, 0x604a, 0x604b, 0x604d, 0x6050, 0x6052, 0x6055,
            0x6059, 0x605a, 0x605f, 0x6060, 0x6062, 0x6063, 0x6064, 0x6065, 0x6068, 0x6069, 0x606a, 0x606b, 0x606c,
            0x606d, 0x606f, 0x6070, 0x6075, 0x6077, 0x6081, 0x6083, 0x6084, 0x6089, 0x608b, 0x608c, 0x608d, 0x6092,
            0x6094, 0x6096, 0x6097, 0x609a, 0x609b, 0x609f, 0x60a0, 0x60a3, 0x60a6, 0x60a7, 0x60a9, 0x60aa, 0x60b2,
            0x60b3, 0x60b4, 0x60b5, 0x60b6, 0x60b8, 0x60bc, 0x60bd, 0x60c5, 0x60c6, 0x60c7, 0x60d1, 0x60d3, 0x60d8,
            0x60da, 0x60dc, 0x60df, 0x60e0, 0x60e1, 0x60e3, 0x60e7, 0x60e8, 0x60f0, 0x60f1, 0x60f3, 0x60f4, 0x60f6,
            0x60f7, 0x60f9, 0x60fa, 0x60fb, 0x6100, 0x6101, 0x6103, 0x6106, 0x6108, 0x6109, 0x610d, 0x610e, 0x610f,
            0x6115, 0x611a, 0x611b, 0x611f, 0x6121, 0x6127, 0x6128, 0x612c, 0x6134, 0x613c, 0x613d, 0x613e, 0x613f,
            0x6142, 0x6144, 0x6147, 0x6148, 0x614a, 0x614b, 0x614c, 0x614d, 0x614e, 0x6153, 0x6155, 0x6158, 0x6159,
            0x615a, 0x615d, 0x615f, 0x6162, 0x6163, 0x6165, 0x6167, 0x6168, 0x616b, 0x616e, 0x616f, 0x6170, 0x6171,
            0x6173, 0x6174, 0x6175, 0x6176, 0x6177, 0x617e, 0x6182, 0x6187, 0x618a, 0x618e, 0x6190, 0x6191, 0x6194,
            0x6196, 0x6199, 0x619a, 0x61a4, 0x61a7, 0x61a9, 0x61ab, 0x61ac, 0x61ae, 0x61b2, 0x61b6, 0x61ba, 0x61be,
            0x61c3, 0x61c6, 0x61c7, 0x61c8, 0x61c9, 0x61ca, 0x61cb, 0x61cc, 0x61cd, 0x61d0, 0x61e3, 0x61e6, 0x61f2,
            0x61f4, 0x61f6, 0x61f7, 0x61f8, 0x61fa, 0x61fc, 0x61fd, 0x61fe, 0x61ff, 0x6200, 0x6208, 0x6209, 0x620a,
            0x620c, 0x620d, 0x620e, 0x6210, 0x6211, 0x6212, 0x6214, 0x6216, 0x621a, 0x621b, 0x621d, 0x621e, 0x621f,
            0x6221, 0x6226, 0x622a, 0x622e, 0x622f, 0x6230, 0x6232, 0x6233, 0x6234, 0x6238, 0x623b, 0x623f, 0x6240,
            0x6241, 0x6247, 0x6248, 0x6249, 0x624b, 0x624d, 0x624e, 0x6253, 0x6255, 0x6258, 0x625b, 0x625e, 0x6260,
            0x6263, 0x6268, 0x626e, 0x6271, 0x6276, 0x6279, 0x627c, 0x627e, 0x627f, 0x6280, 0x6282, 0x6283, 0x6284,
            0x6289, 0x628a, 0x6291, 0x6292, 0x6293, 0x6294, 0x6295, 0x6296, 0x6297, 0x6298, 0x629b, 0x629c, 0x629e,
            0x62ab, 0x62ac, 0x62b1, 0x62b5, 0x62b9, 0x62bb, 0x62bc, 0x62bd, 0x62c2, 0x62c5, 0x62c6, 0x62c7, 0x62c8,
            0x62c9, 0x62ca, 0x62cc, 0x62cd, 0x62cf, 0x62d0, 0x62d1, 0x62d2, 0x62d3, 0x62d4, 0x62d7, 0x62d8, 0x62d9,
            0x62db, 0x62dc, 0x62dd, 0x62e0, 0x62e1, 0x62ec, 0x62ed, 0x62ee, 0x62ef, 0x62f1, 0x62f3, 0x62f5, 0x62f6,
            0x62f7, 0x62fe, 0x62ff, 0x6301, 0x6302, 0x6307, 0x6308, 0x6309, 0x630c, 0x6311, 0x6319, 0x631f, 0x6327,
            0x6328, 0x632b, 0x632f, 0x633a, 0x633d, 0x633e, 0x633f, 0x6349, 0x634c, 0x634d, 0x634f, 0x6350, 0x6355,
            0x6357, 0x635c, 0x6367, 0x6368, 0x6369, 0x636b, 0x636e, 0x6372, 0x6376, 0x6377, 0x637a, 0x637b, 0x6380,
            0x6383, 0x6388, 0x6389, 0x638c, 0x638e, 0x638f, 0x6392, 0x6396, 0x6398, 0x639b, 0x639f, 0x63a0, 0x63a1,
            0x63a2, 0x63a3, 0x63a5, 0x63a7, 0x63a8, 0x63a9, 0x63aa, 0x63ab, 0x63ac, 0x63b2, 0x63b4, 0x63b5, 0x63bb,
            0x63be, 0x63c0, 0x63c3, 0x63c4, 0x63c6, 0x63c9, 0x63cf, 0x63d0, 0x63d2, 0x63d6, 0x63da, 0x63db, 0x63e1,
            0x63e3, 0x63e9, 0x63ee, 0x63f4, 0x63f6, 0x63fa, 0x6406, 0x640d, 0x640f };

    // �r(0x338e)の文字コード。
    // ラベル用栄養成分情報で特定のラベルフォーマットのみ「�r」を入力可能にするために使用する、
    public static final Character MILLIGRAM_CODE = '\u338e';
}