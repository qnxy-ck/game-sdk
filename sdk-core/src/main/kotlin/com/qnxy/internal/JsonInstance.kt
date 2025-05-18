package com.qnxy.internal

import kotlinx.serialization.json.Json


/**
 * @author Qnxy
 */
internal val jsonInstance = Json { ignoreUnknownKeys = true }

