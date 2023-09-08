package com.tailwagging.breedsguide.viewmodels

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.tailwagging.breedsguide.model.dummyList

class BreedVM : ViewModel() {
    val index  = mutableIntStateOf(0)
    val chosenBreed = mutableStateOf(dummyList[index.intValue])

    fun createTitle(idx: Int, dogBreed: String): String {
        val newIdx = if (idx < 9) {
            " ${idx + 1}"
        }  else "${idx + 1}"

        val newDogBreed = if (dogBreed.contains("\n")){
            dogBreed.replace("\n", "")
        }  else {
            dogBreed
        }

        return "$newIdx. $newDogBreed"
    }

    fun increaseIdx(currentIdx: Int) {
        val newIndex = currentIdx + 1
        index.value  =  if (newIndex < dummyList.size) {
            newIndex
        } else {
            0
        }

        chosenBreed.value = dummyList[index.intValue]
    }

    fun decreaseIdx(currentIdx: Int) {
        val newIndex = currentIdx - 1
        index.value  = if (newIndex >= 0 && newIndex < dummyList.size) {
            newIndex
        } else {
            dummyList.size - 1
        }

        chosenBreed.value = dummyList[index.intValue]
    }

    fun refreshBreed() {
        chosenBreed.value = dummyList[index.intValue]
    }
}