export type Player = {
    id: number,
    firstName: string,
    lastName: string,
    playerStats: Array<Statistic>
}

type Statistic = {
    statName: string,
    statValue : string
}