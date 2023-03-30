package com.example.serene.quiz
//
//class Question(
//    i: Int,
//    s: String,
//    icFlagOfKuwait: Any?,
//    s1: String,
//    s2: String,
//    s3: String,
//    s4: Int,
////    i1: Int
//)

// {
//
//    val questionId: Int
//        get() {
//            return 0
//        }
//
//    val question: String get() {
//        return question
//    }
////        set(value) {
////            val b = value
////        }
//     val image: Int
//        get() {
//            return 0
//
//        }
//    val optionOne: String
//        get() {
//            return optionOne
//        }
//    val optionTwo: String
//        get() {
//            return optionTwo
//        }
//    val optionThree: String
//        get() {
//            return optionThree
//        }
//    val optionFour: String
//        get() {
//            return optionFour
//        }
//    val answer: Int
//        get() {
//            return 0
//        }
//
//}

////correct
//class Question (
//
//    val questionId: Int,
//    val question: String,
//    val image: Int,
//    val optionOne: String,
//    val optionTwo: String,
//    val optionThree: String,
//    val optionFour: String,
////    val optionFive:String,
//    val answer: Int,
//
//    )

class Question (

    val questionId: Int,
    val question: String,
//    val image: Int,
    val optionOne:String,
//    val count1:String,
    val optionTwo: Unit,
    val optionThree: Unit,
    val optionFour: Unit,
//    val optionFive:String,
    val answer: Int,


    val never: Int = 1,
    val almostnever: Int = 2,
    val sometimes: Int = 3,
    val often: Int = 4,
    val count1:Int,
//    get() {
//    TODO()
//}
)
//class Question (
//
//    val questionId: Int,
//    val question: String,
//    val image: Int,
//    val optionOne: Int=0,
//    val optionTwo: Int=1,
//    val optionThree: Int=2,
//    val optionFour: Int=3,
//    val optionFive:Int=4,
//    val answer: Int,
//
//    )

//class Question(
//
//    val questionId: Int,
//    val question: String,
//    val image: Int,
//    val optionOne: Boolean,
////    val optionTwo: Int,
////    val optionThree: String,
////    val optionFour: String,
////    val optionFive:String,
//    val answer: String,
//    val mark:Int,
//
//    )