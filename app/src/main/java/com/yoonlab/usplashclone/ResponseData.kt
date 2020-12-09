package com.yoonlab.usplashclone

import java.net.URL


data class ResponseData(val total: Int, val total_pages: Int, val results: List<RESULT>)

data class RESULT(val urls: URLS, val usr: USER, val tags: TAGS)

data class URLS(val raw: String, val full: String, val regular: String, val small: String, val thumb: String)

data class USER(val id: String, val username: String)

data class TAGS(val type: String, val title: String)