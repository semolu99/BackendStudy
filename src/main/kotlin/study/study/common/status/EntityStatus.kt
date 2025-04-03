package study.study.common.status

enum class DormType(val desc: String) {
    GounA("고운A"),
    GounB("고운B"),
    GounC("고운C"),
    Gyung11("경상11층"),
    Gyung12("경상12층"),
    Gyung13("경상13층"),
    Gyung14("경상14층"),
}

enum class ResultCode(val msg: String) {
    SUCCESS("정상 처리 되었습니다."),
    ERROR("에러가 발생했습니다.")
}