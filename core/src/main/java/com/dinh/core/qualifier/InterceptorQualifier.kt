package com.dinh.core.qualifier

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class QualifiterCustomInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class QualifiterNetworkInterceptor