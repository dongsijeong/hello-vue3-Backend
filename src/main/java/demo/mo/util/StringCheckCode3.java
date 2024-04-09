package demo.mo.util;

/**
 * 【StringCheck基本クラス】<BR>
 * 入力禁止文字列３のリソースクラス<BR>
 * リソースクラスのみで構成されたクラス<BR>
 *
 */
final class StringCheckCode3 {
    private StringCheckCode3() {
    }
    public static final Character[] CODE =
        {0x838a, 0x838e, 0x8393, 0x8396, 0x839a, 0x839e, 0x839f, 0x83a0, 0x83a2, 0x83a8,
            0x83aa, 0x83ab, 0x83b1, 0x83b5, 0x83bd, 0x83c1, 0x83c5, 0x83ca, 0x83cc, 0x83ce, 0x83d3, 0x83d6, 0x83d8,
            0x83dc, 0x83df, 0x83e0, 0x83e9, 0x83eb, 0x83ef, 0x83f0, 0x83f1, 0x83f2, 0x83f4, 0x83f7, 0x83fb, 0x83fd,
            0x8403, 0x8404, 0x8407, 0x840b, 0x840c, 0x840d, 0x840e, 0x8413, 0x8420, 0x8422, 0x8429, 0x842a, 0x842c,
            0x8431, 0x8435, 0x8438, 0x843c, 0x843d, 0x8446, 0x8449, 0x844e, 0x8457, 0x845b, 0x8461, 0x8462, 0x8463,
            0x8466, 0x8469, 0x846b, 0x846c, 0x846d, 0x846e, 0x846f, 0x8471, 0x8475, 0x8477, 0x8479, 0x847a, 0x8482,
            0x8484, 0x848b, 0x8490, 0x8494, 0x8499, 0x849c, 0x849f, 0x84a1, 0x84ad, 0x84b2, 0x84b8, 0x84b9, 0x84bb,
            0x84bc, 0x84bf, 0x84c1, 0x84c4, 0x84c6, 0x84c9, 0x84ca, 0x84cb, 0x84cd, 0x84d0, 0x84d1, 0x84d6, 0x84d9,
            0x84da, 0x84ec, 0x84ee, 0x84f4, 0x84fc, 0x84ff, 0x8500, 0x8506, 0x8511, 0x8513, 0x8514, 0x8515, 0x8517,
            0x8518, 0x851a, 0x851f, 0x8521, 0x8526, 0x852c, 0x852d, 0x8535, 0x853d, 0x8540, 0x8541, 0x8543, 0x8548,
            0x8549, 0x854a, 0x854b, 0x854e, 0x8555, 0x8557, 0x8558, 0x855a, 0x8563, 0x8568, 0x8569, 0x856a, 0x856d,
            0x8577, 0x857e, 0x8580, 0x8584, 0x8587, 0x8588, 0x858a, 0x8590, 0x8591, 0x8594, 0x8597, 0x8599, 0x859b,
            0x859c, 0x85a4, 0x85a6, 0x85a8, 0x85a9, 0x85aa, 0x85ab, 0x85ac, 0x85ae, 0x85af, 0x85b9, 0x85ba, 0x85c1,
            0x85c9, 0x85cd, 0x85cf, 0x85d0, 0x85d5, 0x85dc, 0x85dd, 0x85e4, 0x85e5, 0x85e9, 0x85ea, 0x85f7, 0x85f9,
            0x85fa, 0x85fb, 0x85fe, 0x8602, 0x8606, 0x8607, 0x860a, 0x860b, 0x8613, 0x8616, 0x8617, 0x861a, 0x8622,
            0x862d, 0x862f, 0x8630, 0x863f, 0x864d, 0x864e, 0x8650, 0x8654, 0x8655, 0x865a, 0x865c, 0x865e, 0x865f,
            0x8667, 0x866b, 0x8671, 0x8679, 0x867b, 0x868a, 0x868b, 0x868c, 0x8693, 0x8695, 0x86a3, 0x86a4, 0x86a9,
            0x86aa, 0x86ab, 0x86af, 0x86b0, 0x86b6, 0x86c4, 0x86c6, 0x86c7, 0x86c9, 0x86cb, 0x86cd, 0x86ce, 0x86d4,
            0x86d9, 0x86db, 0x86de, 0x86df, 0x86e4, 0x86e9, 0x86ec, 0x86ed, 0x86ee, 0x86ef, 0x86f8, 0x86f9, 0x86fb,
            0x86fe, 0x8700, 0x8702, 0x8703, 0x8706, 0x8708, 0x8709, 0x870a, 0x870d, 0x8711, 0x8712, 0x8718, 0x871a,
            0x871c, 0x8725, 0x8729, 0x8734, 0x8737, 0x873b, 0x873f, 0x8749, 0x874b, 0x874c, 0x874e, 0x8753, 0x8755,
            0x8757, 0x8759, 0x875f, 0x8760, 0x8763, 0x8766, 0x8768, 0x876a, 0x876e, 0x8774, 0x8776, 0x8778, 0x877f,
            0x8782, 0x878d, 0x879f, 0x87a2, 0x87ab, 0x87af, 0x87b3, 0x87ba, 0x87bb, 0x87bd, 0x87c0, 0x87c4, 0x87c6,
            0x87c7, 0x87cb, 0x87d0, 0x87d2, 0x87e0, 0x87ef, 0x87f2, 0x87f6, 0x87f7, 0x87f9, 0x87fb, 0x87fe, 0x8805,
            0x880d, 0x880e, 0x880f, 0x8811, 0x8815, 0x8816, 0x8821, 0x8822, 0x8823, 0x8827, 0x8831, 0x8836, 0x8839,
            0x883b, 0x8840, 0x8842, 0x8844, 0x8846, 0x884c, 0x884d, 0x8852, 0x8853, 0x8857, 0x8859, 0x885b, 0x885d,
            0x885e, 0x8861, 0x8862, 0x8863, 0x8868, 0x886b, 0x8870, 0x8872, 0x8875, 0x8877, 0x887d, 0x887e, 0x887f,
            0x8881, 0x8882, 0x8888, 0x888b, 0x888d, 0x8892, 0x8896, 0x8897, 0x8899, 0x889e, 0x88a2, 0x88a4, 0x88ab,
            0x88ae, 0x88b0, 0x88b1, 0x88b4, 0x88b5, 0x88b7, 0x88bf, 0x88c1, 0x88c2, 0x88c3, 0x88c4, 0x88c5, 0x88cf,
            0x88d4, 0x88d5, 0x88d8, 0x88d9, 0x88dc, 0x88dd, 0x88df, 0x88e1, 0x88e8, 0x88f2, 0x88f3, 0x88f4, 0x88f8,
            0x88f9, 0x88fc, 0x88fd, 0x88fe, 0x8902, 0x8904, 0x8907, 0x890a, 0x890c, 0x8910, 0x8912, 0x8913, 0x891d,
            0x891e, 0x8925, 0x892a, 0x892b, 0x8936, 0x8938, 0x893b, 0x8941, 0x8943, 0x8944, 0x894c, 0x894d, 0x8956,
            0x895e, 0x895f, 0x8960, 0x8964, 0x8966, 0x896a, 0x896d, 0x896f, 0x8972, 0x8974, 0x8977, 0x897e, 0x897f,
            0x8981, 0x8983, 0x8986, 0x8987, 0x8988, 0x898a, 0x898b, 0x898f, 0x8993, 0x8996, 0x8997, 0x8998, 0x899a,
            0x89a1, 0x89a6, 0x89a7, 0x89a9, 0x89aa, 0x89ac, 0x89af, 0x89b2, 0x89b3, 0x89ba, 0x89bd, 0x89bf, 0x89c0,
            0x89d2, 0x89da, 0x89dc, 0x89dd, 0x89e3, 0x89e6, 0x89e7, 0x89f4, 0x89f8, 0x8a00, 0x8a02, 0x8a03, 0x8a08,
            0x8a0a, 0x8a0c, 0x8a0e, 0x8a10, 0x8a13, 0x8a16, 0x8a17, 0x8a18, 0x8a1b, 0x8a1d, 0x8a1f, 0x8a23, 0x8a25,
            0x8a2a, 0x8a2d, 0x8a31, 0x8a33, 0x8a34, 0x8a36, 0x8a3a, 0x8a3b, 0x8a3c, 0x8a41, 0x8a46, 0x8a48, 0x8a50,
            0x8a51, 0x8a52, 0x8a54, 0x8a55, 0x8a5b, 0x8a5e, 0x8a60, 0x8a62, 0x8a63, 0x8a66, 0x8a69, 0x8a6b, 0x8a6c,
            0x8a6d, 0x8a6e, 0x8a70, 0x8a71, 0x8a72, 0x8a73, 0x8a7c, 0x8a82, 0x8a84, 0x8a85, 0x8a87, 0x8a89, 0x8a8c,
            0x8a8d, 0x8a91, 0x8a93, 0x8a95, 0x8a98, 0x8a9a, 0x8a9e, 0x8aa0, 0x8aa1, 0x8aa3, 0x8aa4, 0x8aa5, 0x8aa6,
            0x8aa8, 0x8aac, 0x8aad, 0x8ab0, 0x8ab2, 0x8ab9, 0x8abc, 0x8abf, 0x8ac2, 0x8ac4, 0x8ac7, 0x8acb, 0x8acc,
            0x8acd, 0x8acf, 0x8ad2, 0x8ad6, 0x8ada, 0x8adb, 0x8adc, 0x8ade, 0x8ae0, 0x8ae1, 0x8ae2, 0x8ae4, 0x8ae6,
            0x8ae7, 0x8aeb, 0x8aed, 0x8aee, 0x8af1, 0x8af3, 0x8af7, 0x8af8, 0x8afa, 0x8afe, 0x8b00, 0x8b01, 0x8b02,
            0x8b04, 0x8b07, 0x8b0c, 0x8b0e, 0x8b10, 0x8b14, 0x8b16, 0x8b17, 0x8b19, 0x8b1a, 0x8b1b, 0x8b1d, 0x8b20,
            0x8b21, 0x8b26, 0x8b28, 0x8b2b, 0x8b2c, 0x8b33, 0x8b39, 0x8b3e, 0x8b41, 0x8b49, 0x8b4c, 0x8b4e, 0x8b4f,
            0x8b56, 0x8b58, 0x8b5a, 0x8b5b, 0x8b5c, 0x8b5f, 0x8b66, 0x8b6b, 0x8b6c, 0x8b6f, 0x8b70, 0x8b71, 0x8b72,
            0x8b74, 0x8b77, 0x8b7d, 0x8b80, 0x8b83, 0x8b8a, 0x8b8c, 0x8b8e, 0x8b90, 0x8b92, 0x8b93, 0x8b96, 0x8b99,
            0x8b9a, 0x8c37, 0x8c3a, 0x8c3f, 0x8c41, 0x8c46, 0x8c48, 0x8c4a, 0x8c4c, 0x8c4e, 0x8c50, 0x8c55, 0x8c5a,
            0x8c61, 0x8c62, 0x8c6a, 0x8c6b, 0x8c6c, 0x8c78, 0x8c79, 0x8c7a, 0x8c7c, 0x8c82, 0x8c85, 0x8c89, 0x8c8a,
            0x8c8c, 0x8c8d, 0x8c8e, 0x8c94, 0x8c98, 0x8c9d, 0x8c9e, 0x8ca0, 0x8ca1, 0x8ca2, 0x8ca7, 0x8ca8, 0x8ca9,
            0x8caa, 0x8cab, 0x8cac, 0x8cad, 0x8cae, 0x8caf, 0x8cb0, 0x8cb2, 0x8cb3, 0x8cb4, 0x8cb6, 0x8cb7, 0x8cb8,
            0x8cbb, 0x8cbc, 0x8cbd, 0x8cbf, 0x8cc0, 0x8cc1, 0x8cc2, 0x8cc3, 0x8cc4, 0x8cc7, 0x8cc8, 0x8cca, 0x8ccd,
            0x8cce, 0x8cd1, 0x8cd3, 0x8cda, 0x8cdb, 0x8cdc, 0x8cde, 0x8ce0, 0x8ce2, 0x8ce3, 0x8ce4, 0x8ce6, 0x8cea,
            0x8ced, 0x8cfa, 0x8cfb, 0x8cfc, 0x8cfd, 0x8d04, 0x8d05, 0x8d07, 0x8d08, 0x8d0a, 0x8d0b, 0x8d0d, 0x8d0f,
            0x8d10, 0x8d13, 0x8d14, 0x8d16, 0x8d64, 0x8d66, 0x8d67, 0x8d6b, 0x8d6d, 0x8d70, 0x8d71, 0x8d73, 0x8d74,
            0x8d77, 0x8d81, 0x8d85, 0x8d8a, 0x8d99, 0x8da3, 0x8da8, 0x8db3, 0x8dba, 0x8dbe, 0x8dc2, 0x8dcb, 0x8dcc,
            0x8dcf, 0x8dd6, 0x8dda, 0x8ddb, 0x8ddd, 0x8ddf, 0x8de1, 0x8de3, 0x8de8, 0x8dea, 0x8deb, 0x8def, 0x8df3,
            0x8df5, 0x8dfc, 0x8dff, 0x8e08, 0x8e09, 0x8e0a, 0x8e0f, 0x8e10, 0x8e1d, 0x8e1e, 0x8e1f, 0x8e2a, 0x8e30,
            0x8e34, 0x8e35, 0x8e42, 0x8e44, 0x8e47, 0x8e48, 0x8e49, 0x8e4a, 0x8e4c, 0x8e50, 0x8e55, 0x8e59, 0x8e5f,
            0x8e60, 0x8e63, 0x8e64, 0x8e72, 0x8e74, 0x8e76, 0x8e7c, 0x8e81, 0x8e84, 0x8e85, 0x8e87, 0x8e8a, 0x8e8b,
            0x8e8d, 0x8e91, 0x8e93, 0x8e94, 0x8e99, 0x8ea1, 0x8eaa, 0x8eab, 0x8eac, 0x8eaf, 0x8eb0, 0x8eb1, 0x8ebe,
            0x8ec5, 0x8ec6, 0x8ec8, 0x8eca, 0x8ecb, 0x8ecc, 0x8ecd, 0x8ed2, 0x8edb, 0x8edf, 0x8ee2, 0x8ee3, 0x8eeb,
            0x8ef8, 0x8efb, 0x8efc, 0x8efd, 0x8efe, 0x8f03, 0x8f05, 0x8f09, 0x8f0a, 0x8f0c, 0x8f12, 0x8f13, 0x8f14,
            0x8f15, 0x8f19, 0x8f1b, 0x8f1c, 0x8f1d, 0x8f1f, 0x8f26, 0x8f29, 0x8f2a, 0x8f2f, 0x8f33, 0x8f38, 0x8f39,
            0x8f3b, 0x8f3e, 0x8f3f, 0x8f42, 0x8f44, 0x8f45, 0x8f46, 0x8f49, 0x8f4c, 0x8f4d, 0x8f4e, 0x8f57, 0x8f5c,
            0x8f5f, 0x8f61, 0x8f62, 0x8f63, 0x8f64, 0x8f9b, 0x8f9c, 0x8f9e, 0x8f9f, 0x8fa3, 0x8fa7, 0x8fa8, 0x8fad,
            0x8fae, 0x8faf, 0x8fb0, 0x8fb1, 0x8fb2, 0x8fb7, 0x8fba, 0x8fbb, 0x8fbc, 0x8fbf, 0x8fc2, 0x8fc4, 0x8fc5,
            0x8fce, 0x8fd1, 0x8fd4, 0x8fda, 0x8fe2, 0x8fe5, 0x8fe6, 0x8fe9, 0x8fea, 0x8feb, 0x8fed, 0x8fef, 0x8ff0,
            0x8ff4, 0x8ff7, 0x8ff8, 0x8ff9, 0x8ffa, 0x8ffd, 0x9000, 0x9001, 0x9003, 0x9005, 0x9006, 0x900b, 0x900d,
            0x900e, 0x900f, 0x9010, 0x9011, 0x9013, 0x9014, 0x9015, 0x9016, 0x9017, 0x9019, 0x901a, 0x901d, 0x901e,
            0x901f, 0x9020, 0x9021, 0x9022, 0x9023, 0x9027, 0x902e, 0x9031, 0x9032, 0x9035, 0x9036, 0x9038, 0x9039,
            0x903c, 0x903e, 0x9041, 0x9042, 0x9045, 0x9047, 0x9049, 0x904a, 0x904b, 0x904d, 0x904e, 0x904f, 0x9050,
            0x9051, 0x9052, 0x9053, 0x9054, 0x9055, 0x9056, 0x9058, 0x9059, 0x905c, 0x905e, 0x9060, 0x9061, 0x9063,
            0x9065, 0x9068, 0x9069, 0x906d, 0x906e, 0x906f, 0x9072, 0x9075, 0x9076, 0x9077, 0x9078, 0x907a, 0x907c,
            0x907d, 0x907f, 0x9080, 0x9081, 0x9082, 0x9083, 0x9084, 0x9087, 0x9089, 0x908a, 0x908f, 0x9091, 0x90a3,
            0x90a6, 0x90a8, 0x90aa, 0x90af, 0x90b1, 0x90b5, 0x90b8, 0x90c1, 0x90ca, 0x90ce, 0x90db, 0x90e1, 0x90e2,
            0x90e4, 0x90e8, 0x90ed, 0x90f5, 0x90f7, 0x90fd, 0x9102, 0x9112, 0x9119, 0x912d, 0x9130, 0x9132, 0x9149,
            0x914a, 0x914b, 0x914c, 0x914d, 0x914e, 0x9152, 0x9154, 0x9156, 0x9158, 0x9162, 0x9163, 0x9165, 0x9169,
            0x916a, 0x916c, 0x9172, 0x9173, 0x9175, 0x9177, 0x9178, 0x9182, 0x9187, 0x9189, 0x918b, 0x918d, 0x9190,
            0x9192, 0x9197, 0x919c, 0x91a2, 0x91a4, 0x91aa, 0x91ab, 0x91af, 0x91b4, 0x91b5, 0x91b8, 0x91ba, 0x91c0,
            0x91c1, 0x91c6, 0x91c7, 0x91c8, 0x91c9, 0x91cb, 0x91cc, 0x91cd, 0x91ce, 0x91cf, 0x91d0, 0x91d1, 0x91d6,
            0x91d8, 0x91db, 0x91dc, 0x91dd, 0x91df, 0x91e1, 0x91e3, 0x91e6, 0x91e7, 0x91f5, 0x91f6, 0x91fc, 0x91ff,
            0x920d, 0x920e, 0x9211, 0x9214, 0x9215, 0x921e, 0x9229, 0x922c, 0x9234, 0x9237, 0x923f, 0x9244, 0x9245,
            0x9248, 0x9249, 0x924b, 0x9250, 0x9257, 0x925a, 0x925b, 0x925e, 0x9262, 0x9264, 0x9266, 0x9271, 0x927e,
            0x9280, 0x9283, 0x9285, 0x9291, 0x9293, 0x9295, 0x9296, 0x9298, 0x929a, 0x929b, 0x929c, 0x92ad, 0x92b7,
            0x92b9, 0x92cf, 0x92d2, 0x92e4, 0x92e9, 0x92ea, 0x92ed, 0x92f2, 0x92f3, 0x92f8, 0x92fa, 0x92fc, 0x9306,
            0x930f, 0x9310, 0x9318, 0x9319, 0x931a, 0x9320, 0x9322, 0x9323, 0x9326, 0x9328, 0x932b, 0x932c, 0x932e,
            0x932f, 0x9332, 0x9335, 0x933a, 0x933b, 0x9344, 0x934b, 0x934d, 0x9354, 0x9356, 0x935b, 0x935c, 0x9360,
            0x936c, 0x936e, 0x9375, 0x937c, 0x937e, 0x938c, 0x9394, 0x9396, 0x9397, 0x939a, 0x93a7, 0x93ac, 0x93ad,
            0x93ae, 0x93b0, 0x93b9, 0x93c3, 0x93c8, 0x93d0, 0x93d1, 0x93d6, 0x93d7, 0x93d8, 0x93dd, 0x93e1, 0x93e4,
            0x93e5, 0x93e8, 0x9403, 0x9407, 0x9410, 0x9413, 0x9414, 0x9418, 0x9419, 0x941a, 0x9421, 0x942b, 0x9435,
            0x9436, 0x9438, 0x943a, 0x9441, 0x9444, 0x9451, 0x9452, 0x9453, 0x945a, 0x945b, 0x945e, 0x9460, 0x9462,
            0x946a, 0x9470, 0x9475, 0x9477, 0x947c, 0x947d, 0x947e, 0x947f, 0x9481, 0x9577, 0x9580, 0x9582, 0x9583,
            0x9587, 0x9589, 0x958a, 0x958b, 0x958f, 0x9591, 0x9593, 0x9594, 0x9596, 0x9598, 0x9599, 0x95a0, 0x95a2,
            0x95a3, 0x95a4, 0x95a5, 0x95a7, 0x95a8, 0x95ad, 0x95b2, 0x95b9, 0x95bb, 0x95bc, 0x95be, 0x95c3, 0x95c7,
            0x95ca, 0x95cc, 0x95cd, 0x95d4, 0x95d5, 0x95d6, 0x95d8, 0x95dc, 0x95e1, 0x95e2, 0x95e5, 0x961c, 0x9621,
            0x9628, 0x962a, 0x962e, 0x962f, 0x9632, 0x963b, 0x963f, 0x9640, 0x9642, 0x9644, 0x964b, 0x964c, 0x964d,
            0x964f, 0x9650, 0x965b, 0x965c, 0x965d, 0x965e, 0x965f, 0x9662, 0x9663, 0x9664, 0x9665, 0x9666, 0x966a,
            0x966c, 0x9670, 0x9672, 0x9673, 0x9675, 0x9676, 0x9677, 0x9678, 0x967a, 0x967d, 0x9685, 0x9686, 0x9688,
            0x968a, 0x968b, 0x968d, 0x968e, 0x968f, 0x9694, 0x9695, 0x9697, 0x9698, 0x9699, 0x969b, 0x969c, 0x96a0,
            0x96a3, 0x96a7, 0x96a8, 0x96aa, 0x96b0, 0x96b1, 0x96b2, 0x96b4, 0x96b6, 0x96b7, 0x96b8, 0x96b9, 0x96bb,
            0x96bc, 0x96c0, 0x96c1, 0x96c4, 0x96c5, 0x96c6, 0x96c7, 0x96c9, 0x96cb, 0x96cc, 0x96cd, 0x96ce, 0x96d1,
            0x96d5, 0x96d6, 0x96d9, 0x96db, 0x96dc, 0x96e2, 0x96e3, 0x96e8, 0x96ea, 0x96eb, 0x96f0, 0x96f2, 0x96f6,
            0x96f7, 0x96f9, 0x96fb, 0x9700, 0x9704, 0x9706, 0x9707, 0x9708, 0x970a, 0x970d, 0x970e, 0x970f, 0x9711,
            0x9713, 0x9716, 0x9719, 0x971c, 0x971e, 0x9724, 0x9727, 0x972a, 0x9730, 0x9732, 0x9738, 0x9739, 0x973d,
            0x973e, 0x9742, 0x9744, 0x9746, 0x9748, 0x9749, 0x9752, 0x9756, 0x9759, 0x975c, 0x975e, 0x9760, 0x9761,
            0x9762, 0x9764, 0x9766, 0x9768, 0x9769, 0x976b, 0x976d, 0x9771, 0x9774, 0x9779, 0x977a, 0x977c, 0x9781,
            0x9784, 0x9785, 0x9786, 0x978b, 0x978d, 0x978f, 0x9790, 0x9798, 0x979c, 0x97a0, 0x97a3, 0x97a6, 0x97a8,
            0x97ab, 0x97ad, 0x97b3, 0x97b4, 0x97c3, 0x97c6, 0x97c8, 0x97cb, 0x97d3, 0x97dc, 0x97ed, 0x97ee, 0x97f2,
            0x97f3, 0x97f5, 0x97f6, 0x97fb, 0x97ff, 0x9801, 0x9802, 0x9803, 0x9805, 0x9806, 0x9808, 0x980c, 0x980f,
            0x9810, 0x9811, 0x9812, 0x9813, 0x9817, 0x9818, 0x981a, 0x9821, 0x9824, 0x982c, 0x982d, 0x9834, 0x9837,
            0x9838, 0x983b, 0x983c, 0x983d, 0x9846, 0x984b, 0x984c, 0x984d, 0x984e, 0x984f, 0x9854, 0x9855, 0x9858,
            0x985b, 0x985e, 0x9867, 0x986b, 0x986f, 0x9870, 0x9871, 0x9873, 0x9874, 0x98a8, 0x98aa, 0x98af, 0x98b1,
            0x98b6, 0x98c3, 0x98c4, 0x98c6, 0x98db, 0x98dc, 0x98df, 0x98e2, 0x98e9, 0x98eb, 0x98ed, 0x98ee, 0x98ef,
            0x98f2, 0x98f4, 0x98fc, 0x98fd, 0x98fe, 0x9903, 0x9905, 0x9909, 0x990a, 0x990c, 0x9910, 0x9912, 0x9913,
            0x9914, 0x9918, 0x991d, 0x991e, 0x9920, 0x9921, 0x9924, 0x9928, 0x992c, 0x992e, 0x993d, 0x993e, 0x9942,
            0x9945, 0x9949, 0x994b, 0x994c, 0x9950, 0x9951, 0x9952, 0x9955, 0x9957, 0x9996, 0x9997, 0x9998, 0x9999,
            0x99a5, 0x99a8, 0x99ac, 0x99ad, 0x99ae, 0x99b3, 0x99b4, 0x99bc, 0x99c1, 0x99c4, 0x99c5, 0x99c6, 0x99c8,
            0x99d0, 0x99d1, 0x99d2, 0x99d5, 0x99d8, 0x99db, 0x99dd, 0x99df, 0x99e2, 0x99ed, 0x99ee, 0x99f1, 0x99f2,
            0x99f8, 0x99fb, 0x99ff, 0x9a01, 0x9a05, 0x9a0e, 0x9a0f, 0x9a12, 0x9a13, 0x9a19, 0x9a28, 0x9a2b, 0x9a30,
            0x9a37, 0x9a3e, 0x9a40, 0x9a42, 0x9a43, 0x9a45, 0x9a4d, 0x9a55, 0x9a57, 0x9a5a, 0x9a5b, 0x9a5f, 0x9a62,
            0x9a64, 0x9a65, 0x9a69, 0x9a6a, 0x9a6b, 0x9aa8, 0x9aad, 0x9ab0, 0x9ab8, 0x9abc, 0x9ac0, 0x9ac4, 0x9acf,
            0x9ad1, 0x9ad3, 0x9ad4, 0x9ad8, 0x9ade, 0x9adf, 0x9ae2, 0x9ae3, 0x9ae6, 0x9aea, 0x9aeb, 0x9aed, 0x9aee,
            0x9aef, 0x9af1, 0x9af4, 0x9af7, 0x9afb, 0x9b06, 0x9b18, 0x9b1a, 0x9b1f, 0x9b22, 0x9b23, 0x9b25, 0x9b27,
            0x9b28, 0x9b29, 0x9b2a, 0x9b2e, 0x9b2f, 0x9b31, 0x9b32, 0x9b3b, 0x9b3c, 0x9b41, 0x9b42, 0x9b43, 0x9b44,
            0x9b45, 0x9b4d, 0x9b4e, 0x9b4f, 0x9b51, 0x9b54, 0x9b58, 0x9b5a, 0x9b6f, 0x9b74, 0x9b83, 0x9b8e, 0x9b91,
            0x9b92, 0x9b93, 0x9b96, 0x9b97, 0x9b9f, 0x9ba0, 0x9ba8, 0x9baa, 0x9bab, 0x9bad, 0x9bae, 0x9bb4, 0x9bb9,
            0x9bc0, 0x9bc6, 0x9bc9, 0x9bca, 0x9bcf, 0x9bd1, 0x9bd2, 0x9bd4, 0x9bd6, 0x9bdb, 0x9be1, 0x9be2, 0x9be3,
            0x9be4, 0x9be8, 0x9bf0, 0x9bf1, 0x9bf2, 0x9bf5, 0x9c04, 0x9c06, 0x9c08, 0x9c09, 0x9c0a, 0x9c0c, 0x9c0d,
            0x9c10, 0x9c12, 0x9c13, 0x9c14, 0x9c15, 0x9c1b, 0x9c21, 0x9c24, 0x9c25, 0x9c2d, 0x9c2e, 0x9c2f, 0x9c30,
            0x9c32, 0x9c39, 0x9c3a, 0x9c3b, 0x9c3e, 0x9c46, 0x9c47, 0x9c48, 0x9c52, 0x9c57, 0x9c5a, 0x9c60, 0x9c67,
            0x9c76, 0x9c78, 0x9ce5, 0x9ce7, 0x9ce9, 0x9ceb, 0x9cec, 0x9cf0, 0x9cf3, 0x9cf4, 0x9cf6, 0x9d03, 0x9d06,
            0x9d07, 0x9d08, 0x9d09, 0x9d0e, 0x9d12, 0x9d15, 0x9d1b, 0x9d1f, 0x9d23, 0x9d26, 0x9d28, 0x9d2a, 0x9d2b,
            0x9d2c, 0x9d3b, 0x9d3e, 0x9d3f, 0x9d41, 0x9d44, 0x9d46, 0x9d48, 0x9d50, 0x9d51, 0x9d59, 0x9d5c, 0x9d5d,
            0x9d5e, 0x9d60, 0x9d61, 0x9d64, 0x9d6c, 0x9d6f, 0x9d72, 0x9d7a, 0x9d87, 0x9d89, 0x9d8f, 0x9d9a, 0x9da4,
            0x9da9, 0x9dab, 0x9daf, 0x9db2, 0x9db4, 0x9db8, 0x9dba, 0x9dbb, 0x9dc1, 0x9dc2, 0x9dc4, 0x9dc6, 0x9dcf,
            0x9dd3, 0x9dd9, 0x9de6, 0x9ded, 0x9def, 0x9df2, 0x9df8, 0x9df9, 0x9dfa, 0x9dfd, 0x9e1a, 0x9e1b, 0x9e1e,
            0x9e75, 0x9e78, 0x9e79, 0x9e7d, 0x9e7f, 0x9e81, 0x9e88, 0x9e8b, 0x9e8c, 0x9e91, 0x9e92, 0x9e93, 0x9e95,
            0x9e97, 0x9e9d, 0x9e9f, 0x9ea5, 0x9ea6, 0x9ea9, 0x9eaa, 0x9ead, 0x9eb8, 0x9eb9, 0x9eba, 0x9ebb, 0x9ebc,
            0x9ebe, 0x9ebf, 0x9ec4, 0x9ecc, 0x9ecd, 0x9ece, 0x9ecf, 0x9ed0, 0x9ed2, 0x9ed4, 0x9ed8, 0x9ed9, 0x9edb,
            0x9edc, 0x9edd, 0x9ede, 0x9ee0, 0x9ee5, 0x9ee8, 0x9eef, 0x9ef4, 0x9ef6, 0x9ef7, 0x9ef9, 0x9efb, 0x9efc,
            0x9efd, 0x9f07, 0x9f08, 0x9f0e, 0x9f13, 0x9f15, 0x9f20, 0x9f21, 0x9f2c, 0x9f3b, 0x9f3e, 0x9f4a, 0x9f4b,
            0x9f4e, 0x9f4f, 0x9f52, 0x9f54, 0x9f5f, 0x9f60, 0x9f61, 0x9f62, 0x9f63, 0x9f66, 0x9f67, 0x9f6a, 0x9f6c,
            0x9f72, 0x9f76, 0x9f77, 0x9f8d, 0x9f95, 0x9f9c, 0x9f9d, 0x9fa0, 0xff01, 0xff03, 0xff04, 0xff05, 0xff06,
            0xff08, 0xff09, 0xff0a, 0xff0b, 0xff0c, 0xff0d, 0xff0e, 0xff0f, 0xff10, 0xff11, 0xff12, 0xff13, 0xff14,
            0xff15, 0xff16, 0xff17, 0xff18, 0xff19, 0xff1a, 0xff1b, 0xff1c, 0xff1d, 0xff1e, 0xff1f, 0xff20, 0xff21,
            0xff22, 0xff23, 0xff24, 0xff25, 0xff26, 0xff27, 0xff28, 0xff29, 0xff2a, 0xff2b, 0xff2c, 0xff2d, 0xff2e,
            0xff2f, 0xff30, 0xff31, 0xff32, 0xff33, 0xff34, 0xff35, 0xff36, 0xff37, 0xff38, 0xff39, 0xff3a, 0xff3b,
            0xff3c, 0xff3d, 0xff3e, 0xff3f, 0xff40, 0xff41, 0xff42, 0xff43, 0xff44, 0xff45, 0xff46, 0xff47, 0xff48,
            0xff49, 0xff4a, 0xff4b, 0xff4c, 0xff4d, 0xff4e, 0xff4f, 0xff50, 0xff51, 0xff52, 0xff53, 0xff54, 0xff55,
            0xff56, 0xff57, 0xff58, 0xff59, 0xff5a, 0xff5b, 0xff5c, 0xff5d, 0xff5e, 0xffe0, 0xffe1, 0xffe2, 0xffe3,
            0xffe5 };
}