export type Player = {
    id: number,
    firstName: string,
    lastName: string,
    playerStats: PlayerStats
}

type PlayerStats = {
    countOfTournamentsPlayed: number,
    countOfTournamentsWon : number,
    countOfCoursesPlayed: number,
    countOfCoursesWon: number,
    countOfHolesPlayed: number,
    countOfHolesWon: number,
}