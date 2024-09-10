import { Course } from "./course"
import { Player } from "./player"

export type Tournament = {
    id: number,
    _year: number, // This might not work as a number and might need to be as a String
    courses: Array<Course>,
    players: Array<Player>
    winner: Player
}