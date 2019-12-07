package com.jordanrosas.kehacer.data.network

import java.io.IOException

class OfflineException :
    IOException("Problema de conectividad.\nComprueba tu conexión a internet e inténtalo de nuevo")
