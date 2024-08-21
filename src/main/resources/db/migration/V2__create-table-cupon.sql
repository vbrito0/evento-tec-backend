CREATE TABLE cupon(
	id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
	codigo VARCHAR(100) NOT NULL,
	desconto INTEGER NOT NULL,
	valido TIMESTAMP NOT NULL,
	evento_id UUID,
	FOREIGN KEY (evento_id) REFERENCES evento(id) ON DELETE CASCADE
);