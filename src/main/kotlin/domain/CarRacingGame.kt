package domain

import data.Car
import data.CarPath
import data.CarRacingGamePlayer

class CarRacingGame {

    fun startDriving(players: List<CarRacingGamePlayer>): List<CarPath> {
        val carsPath = mutableListOf<CarPath>()

        players.forEach { player ->
            carsPath.add(player.moveCar())
        }

        return carsPath.toList()
    }

    fun decideWinner(cars: List<Car>): List<String> {
        val maxScore = cars.getMaxScore()
        val winners = cars.filter { car ->
            car.position == maxScore
        }.map { car ->
            car.name
        }

        return winners
    }

    private fun List<Car>.getMaxScore(): Int = this.maxBy { car ->
        car.position
    }.position
}
