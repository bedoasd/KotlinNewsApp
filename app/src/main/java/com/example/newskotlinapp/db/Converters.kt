package com.example.newskotlinapp.db

import androidx.room.TypeConverter
import com.example.newskotlinapp.models.Source
class Converters  {

    @TypeConverter
    fun fromSource(source: Source):String?{
        return source.name
    }
    @TypeConverter //converts between Primitive  and boxed(imPrimitive)types
    fun toSource(name :String):Source{
        return Source(name,name)
    }

}