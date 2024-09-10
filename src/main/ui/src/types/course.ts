import { Hole } from "./hole"
import { Player } from "./player"

export type Course = {
    id: number,
    courseName: string,
    holes: Array<Hole>,
    winnerByTournamentId: {
        index: number,
        player: Player
    }
}