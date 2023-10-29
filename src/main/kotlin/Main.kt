fun main() {
    println("Введите координаты трех вершин треугольника:")

    val triangleVertices = mutableListOf<Pair<Double, Double>>()
    for (i in 1..3) {
        val coordinates = readCoordinates(i)
        if (coordinates == null) {
            println("Некорректные координаты. Попробуйте еще раз.")
            return
        }
        triangleVertices.add(coordinates)
    }

    println("Введите координаты точки для проверки:")
    val pointCoordinates = readCoordinates()

    if (pointCoordinates == null) {
        println("Некорректные координаты точки. Попробуйте еще раз.")
        return
    }

    val isInsideTriangle = isPointInsideTriangle(pointCoordinates, triangleVertices)

    if (isInsideTriangle) {
        println("Точка находится внутри треугольника.")
    } else {
        println("Точка находится вне треугольника.")
    }
}

fun readCoordinates(vertexNumber: Int = 0): Pair<Double, Double>? {
    print("Введите координаты вершины $vertexNumber (x, y): ")
    val input = readLine()
    val coordinates = input?.split(" ")

    if (coordinates != null && coordinates.size == 2) {
        val x = coordinates[0].toDoubleOrNull()
        val y = coordinates[1].toDoubleOrNull()

        if (x != null && y != null) {
            return Pair(x, y)
        }
    }

    return null
}

fun isPointInsideTriangle(point: Pair<Double, Double>, triangle: List<Pair<Double, Double>>): Boolean {
    val a = triangle[0]
    val b = triangle[1]
    val c = triangle[2]

    val areaABC = 0.5 * Math.abs((a.first * (b.second - c.second) + b.first * (c.second - a.second) + c.first * (a.second - b.second)))
    val areaPBC = 0.5 * Math.abs((point.first * (b.second - c.second) + b.first * (c.second - point.second) + c.first * (point.second - b.second)))
    val areaPCA = 0.5 * Math.abs((a.first * (point.second - c.second) + point.first * (c.second - a.second) + c.first * (a.second - point.second)))
    val areaPAB = 0.5 * Math.abs((a.first * (b.second - point.second) + b.first * (point.second - a.second) + point.first * (a.second - b.second)))

    return areaABC == areaPBC + areaPCA + areaPAB
}