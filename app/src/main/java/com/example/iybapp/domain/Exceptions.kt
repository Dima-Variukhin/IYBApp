package com.example.iybapp.domain

import java.io.IOException

class NoConnectionException : IOException()
class ServiceUnavailableException : IOException()
class NoCachedActionsException : IOException()