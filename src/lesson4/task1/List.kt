@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr
import lesson3.task1.digitNumber
import lesson3.task1.minDivisor
import org.junit.Test
import kotlin.math.pow
import kotlin.math.sqrt

// Урок 4: списки
// Максимальное количество баллов = 12
// Рекомендуемое количество баллов = 8
// Вместе с предыдущими уроками = 24/33

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая (2 балла)
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    val result = mutableListOf<Double>()
    for (element in v)
        result.add(sqr(element))
    return sqrt(result.sum())
}

/**
 * Простая (2 балла)
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double = if (list.isEmpty()) 0.0
else list.sum() / list.size


/**
 * Средняя (3 балла)
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    val average = mean(list)
    for (i in 0 until list.size) {
        list[i] -= average
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    var result = 0
    for (i in a.indices) {
        result += a[i] * b[i]
    }
    return result
}

/**
 * Средняя (3 балла)
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    var result = 0
    for (i in p.indices)
        result += p[i] * x.toDouble().pow(i).toInt()
    return result
}

/**
 * Средняя (3 балла)
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    var sum = 0
    for (i in list.indices) {
        sum += list[i]
        list[i] = sum
    }
    return list
}

/**
 * Средняя (3 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */

fun factorize(n: Int): List<Int> {
    val list = mutableListOf<Int>()
    var m = n
    while (m > 1) {
        list.add(minDivisor(m))
        m /= minDivisor(m)
    }
    return list
}

/**
 * Сложная (4 балла)
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    val list = factorize(n)
    return list.joinToString(separator = "*")
}

/**
 * Средняя (3 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */

fun convert(n: Int, base: Int): List<Int> {
    val list = mutableListOf<Int>()
    var m = n
    do {
        val remain = m % base
        list.add(remain)
        m /= base
    } while (m > 0)
    return list.reversed()
}

/**
 * Сложная (4 балла)
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */

fun convertToString(n: Int, base: Int): String {
    var m = n
    var res = ""
    do {
        val remain = m % base
        if (remain < 10) res += "$remain"
        else {
            res += (remain + 87).toChar()
        }
        m /= base
    } while (m > 0)
    return res.reversed()
}

/**
 * Средняя (3 балла)
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var res = 0
    for (i in digits.indices)
        res += digits[i] * base.toDouble().pow(digits.size - i - 1).toInt()
    return res
}

/**
 * Сложная (4 балла)
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */

fun decimalFromString(str: String, base: Int): Int {
    var res = 0
    var num: Int
    for (i in str.indices) {
        num = if (str[i].toInt() > 96) str[i].toInt() - 87
        else str[i].toInt() - 48
        res += num * base.toDouble().pow(str.length - i - 1).toInt()
    }
    return res
}

/**
 * Сложная (5 баллов)
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun baseRoman(n: Int): String {
    return when (n) {
        1 -> "I"; 4 -> "IV"; 5 -> "V"; 9 -> "IX"; 10 -> "X"
        40 -> "XL"; 50 -> "L"; 90 -> "XC"; 100 -> "C"; 400 -> "CD"; 500 -> "D"
        900 -> "CM"; 1000 -> "M"
        else -> ""
    }
}

fun roman(n: Int): String {
    var res = ""
    var m = n
    var i = 3 - digitNumber(n) //Пришлось немного доработать, так как в прошлая версия некорректно работала при n > 9999
    if (i < 0) i = 0
    while (m > 0) {
        val power = 10.0.pow(3 - i).toInt()
        val powerLow = 10.0.pow(2 - i).toInt()
        while (m >= power) {
            res += baseRoman(1 * power)
            m -= power
        }
        if (m >= 9 * powerLow) {
            res += baseRoman(9 * powerLow)
            m -= 9 * powerLow
        }
        if (m >= 5 * powerLow) {
            res += baseRoman(5 * powerLow)
            m -= 5 * powerLow
        }
        if (m >= 4 * powerLow) {
            res += baseRoman(4 * powerLow)
            m -= 4 * powerLow
        }
        i++
    }
    return res
}

/**
 * Очень сложная (7 баллов)
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun rusLang(n: Int): String {
    return when (n) {
        1 -> "один"; 2 -> "два"; 3 -> "три"; 4 -> "четыре"; 5 -> "пять"; 0 -> ""
        6 -> "шесть"; 7 -> "семь"; 8 -> "восемь"; 9 -> "девять"; 10 -> "десять"
        11 -> "одиннадцать"; 12 -> "двенадцать"; 13 -> "тринадцать"; 14 -> "четырнадцать"
        15 -> "пятнадцать"; 16 -> "шестнадцать"; 17 -> "семнадцать"
        18 -> "восемнадцать"; 19 -> "девятнадцать"
        20 -> "двадцать"; 30 -> "тридцать"; 40 -> "сорок"; 50 -> "пятьдесят"; 60 -> "шестьдесят"
        70 -> "семьдесят"; 80 -> "восемьдесят"; 90 -> "девяносто"
        in 20..99 -> rusLang(n / 10) + rusLang(n % 10)
        100 -> "сто"; 200 -> "двести"; 300 -> "триста"; 400 -> "четыреста"
        500 -> "пятьсот"; 600 -> "шестьсот"; 700 -> "семьсот"; 800 -> "восемьсот"
        900 -> "девятьсот"; 1000 -> "одна тысяча"; 2000 -> "две тысячи"
        3000, 4000 -> rusLang(n / 1000) + " тысячи"; in 5000..9000 step 1000 -> rusLang(n / 1000) + " тысяч"
        in 10000..19000 step 1000 -> rusLang(n / 1000) + " тысяч"
        in 20000..90000 step 10000 -> rusLang(n / 1000) + " тысяч"
        in 100000..900000 step 100000 -> rusLang(n / 1000) + " тысяч"
        else -> ""
    }
}

fun russian(n: Int): String {
    var res = ""
    var str1 = ""
    var str2 = ""
    val thous = n / 1000
    val num = n % 1000
    if (thous > 0) {
        when {
            thous % 100 == 0 -> str1 += rusLang(thous * 1000)
            thous % 10 == 0 -> str1 += rusLang(thous - thous % 100) + " " + rusLang(thous % 100 * 1000)
            else -> {
                str1 += rusLang(thous - thous % 100)
                str2 += if ((thous % 100 > 9) && (thous % 100 < 20)) rusLang(thous % 100 * 1000)
                else rusLang(thous % 100 - thous % 10) + " " + rusLang(thous % 10 * 1000)
            }
        }

    }
    val str3 = rusLang(num - num % 100)
    val str4 = if ((num % 100 >= 0) && (num % 100 < 20)) rusLang(num % 100)
    else rusLang(num % 100 - num % 10) + " " + rusLang(num % 10)
    if (str1.isNotEmpty()) res += str1.trim() + " "
    if (str2.isNotEmpty()) res += str2.trim() + " "
    if (str3.isNotEmpty()) res += str3.trim() + " "
    if (str4.isNotEmpty()) res += str4.trim()
    return res.trim()
}