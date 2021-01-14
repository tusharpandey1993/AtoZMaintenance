package com.base.AtoZMaintenanceApp.imagePicker;

class EasyImageException(message: String, cause: Throwable?) : Throwable(message, cause) {
    constructor(cause: Throwable) : this(cause.message ?: "", cause)
    constructor(message: String) : this(message, null)
}