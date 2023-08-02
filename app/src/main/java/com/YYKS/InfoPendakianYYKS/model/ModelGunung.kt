package com.YYKS.InfoPendakianYYKS.model

import java.io.Serializable



class ModelGunung : Serializable {
    lateinit var strNamaGunung: String
    lateinit var strImageGunung: String
    lateinit var strLokasiGunung: String
    lateinit var strDeskripsi: String
    lateinit var strJalurPendakian: String
    lateinit var strInfoGunung: String
    var strLat = 0.0
    var strLong = 0.0
}