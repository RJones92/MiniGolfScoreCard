import { Hole } from "./hole";
import { Player } from "./player";
import { Tournament } from "./tournament";

export type Score = {
    id: number,
    player: Player,
    tournament: Tournament, 
    hole: Hole,
    strokes: number
}