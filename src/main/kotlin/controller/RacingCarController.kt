package controller

import domain.CarRacingGame
import domain.Initialize
import domain.Referee
import model.Car
import model.CarPath
import view.InputView.inputCarNames
import view.InputView.inputNumberOfTry
import view.OutputView

class RacingCarController(
    private val carRacingGame: CarRacingGame = CarRacingGame(),
    private val initializer: Initialize = Initialize()
) {

    fun startGame() {
        try {
            val cars = initializer.initCars(inputCarNames())
            val numberOfTry = initializer.initNumberOfTry(inputNumberOfTry())
            val carsPath = carRacingGame.startDriving(cars, numberOfTry)

            showPath(carsPath, numberOfTry)
            cars.showWinner()
        } catch (e: IllegalArgumentException) {
            OutputView.printMsg(e.message!!)
        }
    }

    private fun List<Car>.showWinner() =
        OutputView.printWinner(Referee().decideWinner(this))

    private fun showPath(carsPath: List<CarPath>, numberOfTry: Int) {
        OutputView.printMsg(RESULT)

        repeat(numberOfTry) { number ->
            OutputView.printResult(carsPath, number)
        }
    }

    companion object {
        const val RESULT = "실행 결과\n"
    }
}