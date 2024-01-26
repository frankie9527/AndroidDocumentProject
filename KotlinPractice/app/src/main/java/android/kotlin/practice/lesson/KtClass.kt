package android.kotlin.practice.lesson

class KtClass
class Contact(val id: Int, var email: String)

fun main(){
    val clazz =KtClass();
    val contact=Contact(1,"847145851@qq.com");
    println(contact.id)
    contact.email="hello@qq.com"
    println("email =${contact.email}")

}