package com.example.iybapp.data.cache

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class QuoteRealmModel : RealmObject() {
    @PrimaryKey
    var key: String = ""
    var content: String = ""
    var author: String = ""
  //  override fun to() = CommonDataModel(key, 0, 0.0, content, author, true)
}