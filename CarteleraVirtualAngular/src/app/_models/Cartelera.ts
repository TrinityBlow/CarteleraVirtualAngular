import { Categoria } from '_models/Categoria'
import { Publicacion } from '_models/Publicacion'

export interface Cartelera {
    id: number;
    titulo: string;
    creador: string;
    fecha: number;
    categoria: Categoria;
    publicaciones: Publicacion[];
}