package android.kotlin.practice.lesson

fun  main(){
    val str :String ="hello";
    println("str ="+str);
    println("str size =${str.length}");

    val numInt :Int =1;
    println("numInt ="+numInt);

    val numDouble :Double =2.32;
    println("numDouble ="+numDouble);

    val numFloat :Float =1.111f;
    println("numFloat ="+numFloat);

    val strBoolean :Boolean =true;
    println("strBoolean ="+strBoolean);

    var nullable: String? = "You can keep a null here"      // 3
    nullable = null

}