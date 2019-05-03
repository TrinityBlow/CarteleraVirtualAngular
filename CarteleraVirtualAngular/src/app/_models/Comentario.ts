import { SimpleUser } from '_models/SimpleUser'

export interface Comentario {
    id: number;
    comentario: string;
    respuesta: string;	
    fechaComentario: Date;	
    fechaRespuesta: Date;	
    alumno: SimpleUser;
    respondio: SimpleUser;
}