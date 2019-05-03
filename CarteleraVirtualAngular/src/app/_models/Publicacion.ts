import { SimpleUser } from './SimpleUser';

export interface Publicacion {
    id: number;
    titulo: string;
    nota: string;
    comHabilitado: number;
    fecha: number;
    imagen: String;
    creador: SimpleUser;
}