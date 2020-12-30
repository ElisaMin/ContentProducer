package com.example.providers.annotations

/** 传送者使用的AnoClasses **/

/**
 * 访问contentProducer的QueryController
 * 并设置[path]
 */
annotation class GET(val path:String)

/**
 * 访问contentProducer的InsertController
 * 并设置[path]
 */
annotation class PUT(val path:String)

/**
 * Param
 *
 */
annotation class Param(val name:String)