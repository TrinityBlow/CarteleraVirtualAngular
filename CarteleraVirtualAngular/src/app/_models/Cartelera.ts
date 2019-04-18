import { Categoria } from '_models/Categoria'

export interface Cartelera {
    id: number;
    titulo: string;
    creador: string;
    categoria: Categoria;
}