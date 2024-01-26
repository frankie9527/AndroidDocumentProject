package android.kotlin.practice.lesson.collection

fun main(){
    val numbers = listOf(1, -2, 3, -4, 5, -6)      // 1

    val positives = numbers.filter { x -> x > 0 }  // 2

    positives.forEach{
        i -> println("positives data $i")
    }
    val negatives = numbers.filter { it < 0 }      // 3
    negatives.forEach{
            i -> println("negatives data $i")
    }
}