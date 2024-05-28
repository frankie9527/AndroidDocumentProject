package android.kotlin.practice.lesson.collection


import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.dropWhile

import kotlinx.coroutines.flow.flowOf

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.take

import kotlinx.coroutines.flow.transform
import kotlinx.coroutines.runBlocking


/**
 * @author: Frankie
 * @Date: 2024/5/29
 * @Description:
 */

/**
 * flowOf 把填入的数据整体作为一个flow
 * */
fun practice1() {
    runBlocking {
        flowOf(1, 2, 3).collect {
            println("kt practice1 data $it")
        }
    }
}

/**
 * asFlow  把数据转化为flow
 * map 和rxjava 里面一样获取单个对象 可以进行转换
 * */
fun practice2() {
    runBlocking {
        (0..10).asFlow().map { it + 1 }.collect {
            println("kt practice2 data $it")
        }
    }
}

/**
 * Flow 的 transform 操作符允许你对流中的每个元素应用一个转换函数，并且这个转换函数本身可以返回一个Flow
 * 简而言之就是一个新的flow{},你想干啥都可以
 * */
fun practice3() {
    runBlocking {
        flowOf(1, 2, 3, 4, 5, 6).transform { it ->
            emit(it)
            delay(1000)
            emit(int2String(it))
        }.collect {
            println("kt practice2 data $it")
        }
    }
}

/**
 *  takeIf 满足条件的留下
 *
 * */
fun practice4() {
    runBlocking {
        (0..10).asFlow().takeIf {
            true
        }?.collect {
            println("kt practice4 data $it")
        }
    }
}

/**
 *  take 获取前五个
 * */
fun practice5() {
    runBlocking {
        (0..10).asFlow().take(5).collect {
            println("kt practice5 data $it")
        }
    }
}

/**
 *  drop 不要前五个
 * */
fun practice6() {
    runBlocking {
        runBlocking {
            (0..10).asFlow().drop(5).collect {
                println("kt practice6 data $it")
            }
        }
    }
}


/**
 *  dropWhile
 *  it <5 意思是小于5的不要
 * */
fun practice7() {
    runBlocking {
        runBlocking {
            (0..10).asFlow().dropWhile {
                it<5
            }.collect {
                println("kt practice6 data $it")
            }
        }
    }
}

fun main() {
    practice7()
}
fun int2String(data: Int): String {
    return "deal data" + data;
}
