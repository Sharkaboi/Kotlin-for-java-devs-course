package nicestring

fun String.isNice(): Boolean {
    return (cond1(this)&&cond2(this)|| cond1(this)&&cond3(this) || cond2(this)&&cond3(this))
}

fun cond3(s: String): Boolean {
    var count=0
    for(i in 0..s.length-2){
        if(s[i]==s[i+1])
            count++
    }
    return count>=1
}

fun cond2(s: String): Boolean {
    var count=0
    s.forEach {
        if (it == 'a' || it=='e' || it=='i' || it=='o' || it =='u')
            count++
    }
    return count>=3
}

fun cond1(s:String):Boolean{
    return !(s.contains("bu")||s.contains("ba")||s.contains("be"))
}