package ttps.spring.itemsSerializers;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import ttps.spring.modelCartelera.Cartelera;
import ttps.spring.modelCartelera.Categoria;

public class CategoriaItemSerializer extends StdSerializer<Categoria> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CategoriaItemSerializer() {
        this(null);
    }
   
    public CategoriaItemSerializer(Class<Categoria> t) {
        super(t);
    }

	@Override
	public void serialize(Categoria value, JsonGenerator gen, SerializerProvider provider) throws IOException {

		List<Cartelera> carteleras = value.getCarteleras();
		
		gen.writeStartObject();
		gen.writeNumberField("id", value.getId());
		gen.writeStringField("tipo", value.getTipo());
		gen.writeNumberField("activo", value.getActivo());
		gen.writeArrayFieldStart("carteleras");
		for(Cartelera cartelera: carteleras) {
			gen.writeStartObject();
			gen.writeNumberField("id", cartelera.getId());
			gen.writeStringField("titulo", cartelera.getTitulo());
			gen.writeEndObject();
		}
		gen.writeEndArray();
		gen.writeEndObject();
		
	}
	
}
