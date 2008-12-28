package br.org.cesar.jedje.compiler.parser;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import br.org.cesar.jedje.compiler.JEdjeException;
import br.org.cesar.jedje.compiler.grammar.JEdjeColor;
import br.org.cesar.jedje.compiler.grammar.JEdjeDescription;
import br.org.cesar.jedje.compiler.grammar.JEdjeDocument;
import br.org.cesar.jedje.compiler.grammar.JEdjeGroup;
import br.org.cesar.jedje.compiler.grammar.JEdjeImage;
import br.org.cesar.jedje.compiler.grammar.JEdjePart;
import br.org.cesar.jedje.compiler.grammar.JEdjeProgram;
import br.org.cesar.jedje.compiler.grammar.JEdjeRel;
import br.org.cesar.jedje.compiler.grammar.JEdjeTuple;

public class JEdjeParser {
	
	private JEdjeScanner scanner;
	private JEdjeToken	 current;
	
	private Vector parts;
	
	public JEdjeParser(JEdjeScanner _scanner) {
		this.scanner = _scanner;
	}
	
	public JEdjeDocument parseDocument() throws IOException, JEdjeException {
		JEdjeDocument document = null;
		JEdjeImage[] images = parseImages();
		JEdjeGroup[] groups = parseGroups();
		
		document = new JEdjeDocument(images, groups);
		return document;
	}

	private JEdjeGroup[] parseGroups() throws IOException, JEdjeException {
		if (current != JEdjeToken.COLLECTIONS) {
			return null;
		}
		current = this.scanner.scan();
		if (current != JEdjeToken.LEFT_KEY) {
			throw new JEdjeException("Left key expected.");
		}
		
		Vector groups = new Vector();
		current = this.scanner.scan();
		parseGroupEntries(groups);
		
		if (current != JEdjeToken.RIGHT_KEY) {
			throw new JEdjeException("Right key expected.");
		}
		
		JEdjeGroup[] result = new JEdjeGroup[groups.size()];
		groups.copyInto(result);
		return result;
	}

	private void parseGroupEntries(Vector images) throws IOException, JEdjeException {
		while (current == JEdjeToken.GROUP) {
			JEdjeProgram[] programs = null;
			JEdjePart[]    parts 	= null;
			JEdjeTuple 	   min 		= null;
			JEdjeTuple 	   max 		= null;
			String 		   name 	= null;
			
			current = this.scanner.scan();
			if (current != JEdjeToken.LEFT_KEY) {
				throw new JEdjeException("Left key expected.");
			}
			
			boolean processNext = true;
			while (current != JEdjeToken.RIGHT_KEY) {
				if (processNext) {					
					current = this.scanner.scan();
				} else {
					processNext = true;
				}
				if (current == JEdjeToken.NAME) {
					name = parseName();
				} else
				if (current == JEdjeToken.MIN) {
					min = parseTuple();
				} else
				if (current == JEdjeToken.MAX) {
					max = parseTuple();
				} else
				if (current == JEdjeToken.PROGRAMS) {
					programs = parseGroupPrograms();
					processNext = false;
				} else
				if (current == JEdjeToken.PARTS) {
					parts = parseGroupParts();
					processNext = false;
				}
			}
			
			if (current != JEdjeToken.RIGHT_KEY) {
				throw new JEdjeException("Right key expected.");
			}
			
			images.addElement(new JEdjeGroup(name, min, max, programs, parts));
			current = this.scanner.scan();
		}
	}

	private JEdjeTuple parseTuple() throws IOException, JEdjeException {
		current = this.scanner.scan();
		if (current != JEdjeToken.COMMA) {
			throw new JEdjeException("Expecting min|max, int int;");
		}
		
		current = this.scanner.scan();
		if (current.getType() != JEdjeToken.FLOAT && 
				current.getType() != JEdjeToken.INTEGER) {
			throw new JEdjeException("Expecting <keyword>, num num;");
		}
		float param1 = Float.parseFloat(current.getValue());
		
		current = this.scanner.scan();
		if (current.getType() != JEdjeToken.FLOAT &&
				current.getType() != JEdjeToken.INTEGER) {
			throw new JEdjeException("Expecting <keyword>, num num;");
		}
		float param2 = Float.parseFloat(current.getValue());
		
		current = this.scanner.scan();
		if (current != JEdjeToken.SEMI_COLLON) {
			throw new JEdjeException("Expecting <keyword>, num num;");
		}
		return new JEdjeTuple(param1, param2);
	}

	private JEdjePart[] parseGroupParts() throws IOException, JEdjeException {
		current = this.scanner.scan();
		if (current != JEdjeToken.LEFT_KEY) {
			throw new JEdjeException("Left key expected.");
		}
		
		this.parts = new Vector();
		current = this.scanner.scan();
		parseGroupPart(parts);
		
		if (current != JEdjeToken.RIGHT_KEY) {
			throw new JEdjeException("Right key expected.");
		}
		
		JEdjePart[] result = new JEdjePart[parts.size()];
		parts.copyInto(result);
		
		current = this.scanner.scan();
		return result;
	}

	private void parseGroupPart(Vector parts) throws IOException, JEdjeException {
		while (current == JEdjeToken.PART) {
			
			Vector 	descriptions  = null;
			boolean repeateEvents = false;
			boolean mouseEvents   = false;
			String  name   		  = null;
			int	    effect 		  = 0x00;
			int	    type   		  = 0x00;
			
			current = this.scanner.scan();
			if (current != JEdjeToken.LEFT_KEY) {
				throw new JEdjeException("Left key expected.");
			}
			
			descriptions = new Vector();
			boolean processNext = true;
			while (current != JEdjeToken.RIGHT_KEY) {
				if (processNext) {					
					current = this.scanner.scan();
				} else {
					processNext = true;
				}
				if (current == JEdjeToken.NAME) {
					name = parseName();
				} else
				if (current == JEdjeToken.TYPE) {
					type = parsePartType();
				} else
				if (current == JEdjeToken.EFFECT) {
					effect = JEdjeDescription.parseEffect(this.current.getValue());
				} else
				if (current == JEdjeToken.REPEAT_EVENTS) {
					repeateEvents = parseBooleanParam();
				} else
				if (current == JEdjeToken.MOUSE_EVENTS) {
					mouseEvents = parseBooleanParam();
				} else
				if (current == JEdjeToken.DESCRIPTION) {
					parseDescription(descriptions);
					processNext = false;
				}
			}
			
			JEdjeDescription[] array = new JEdjeDescription[descriptions.size()];
			descriptions.copyInto(array);
			parts.addElement(new JEdjePart(name, type, effect, repeateEvents, mouseEvents, null, array));
			current = this.scanner.scan();
		}
	}

	private void parseDescription(Vector descriptions) throws IOException, JEdjeException {
		
		String name 	  = null;
		boolean visible   = true;
		JEdjeTuple align  = null;
		JEdjeTuple min	  = null;
		JEdjeTuple max	  = null;
		JEdjePart inherit = null;
		JEdjeColor color  = null;
		JEdjeRel rel1 	  = null;
		JEdjeRel rel2 	  = null;
		
		current = this.scanner.scan();
		if (current != JEdjeToken.LEFT_KEY) {
			throw new JEdjeException("Left key expected.");
		}

		boolean processNext = true;
		while (current != JEdjeToken.RIGHT_KEY) {
			if (processNext) {				
				current = this.scanner.scan();
			} else {
				processNext = true;
			}
			if (current == JEdjeToken.STATE) {
				name = parseState();
			} else
			if (current == JEdjeToken.VISIBLE) {
				visible = parseBooleanParam();
			} else
			if (current == JEdjeToken.ALIGN) {
				align = parseTuple();
			} else
			if (current == JEdjeToken.MIN) {
				min = parseTuple();
			} else				
			if (current == JEdjeToken.MAX) {
				max = parseTuple();
			} else
			if (current == JEdjeToken.INHERIT) {
				throw new JEdjeException("description inherit not suported.");
			} else
			if (current == JEdjeToken.COLOR) {
				color = parseColor();
			} else
			if (current == JEdjeToken.REL1) {
				rel1 = parseRel();
				processNext = false;
			} else
			if (current == JEdjeToken.REL2) {
				rel2 = parseRel();
				processNext = false;
			}
		}
		
		if (current != JEdjeToken.RIGHT_KEY) {
			throw new JEdjeException("Right key expected.");
		}
		
		descriptions.addElement(new JEdjeDescription(name, visible, align, min
				, max, inherit, color, rel1, rel2));
		current = this.scanner.scan();
	}

	private String parseState() throws IOException, JEdjeException {
		current = this.scanner.scan();
		if (current != JEdjeToken.COMMA) {
			throw new JEdjeException("Expecting state, <name> <index>;");
		}
		
		current = this.scanner.scan();
		if (current.getType() != JEdjeToken.IDENTIFIER) {
			throw new JEdjeException("Expecting state, <name> <index>;");
		}
		String name = current.getValue();
		
		current = this.scanner.scan();
		if (current.getType() != JEdjeToken.FLOAT) {
			throw new JEdjeException("Expecting state, <name> <index>;");
		}
		float index = Float.parseFloat(current.getValue());
		
		current = this.scanner.scan();
		if (current != JEdjeToken.SEMI_COLLON) {
			throw new JEdjeException("Expecting state, <name> <index>;");
		}
		return name;
	}

	private JEdjeRel parseRel() throws IOException, JEdjeException {
		
		JEdjeTuple relative = null;
		JEdjeTuple offset 	= null;;
		JEdjePart  to 		= null;
		JEdjePart  to_x 	= null;;
		JEdjePart  to_y 	= null;;
		
		current = this.scanner.scan();
		if (current != JEdjeToken.LEFT_KEY) {
			throw new JEdjeException("Left key expected.");
		}
		
		while (current != JEdjeToken.RIGHT_KEY) {			
			current = this.scanner.scan();
			if (current == JEdjeToken.RELATIVE) {
				relative = parseTuple();
			} else
			if (current == JEdjeToken.OFFSET) {
				offset = parseTuple();
			} else
			if (current == JEdjeToken.TO) {
				to = parsePartReference();
			} else
			if (current == JEdjeToken.TO_X) {
				to_x = parsePartReference();
			} else
			if (current == JEdjeToken.TO_Y) {
				to_y = parsePartReference();
			}
		}
		
		if (current != JEdjeToken.RIGHT_KEY) {
			throw new JEdjeException("Right key expected.");
		}
		current = this.scanner.scan();
		return new JEdjeRel(relative, offset, to, to_x, to_y);
	}

	private JEdjePart parsePartReference() throws IOException, JEdjeException {
		current = this.scanner.scan();
		if (current != JEdjeToken.COMMA) {
			throw new JEdjeException("Expecting <reference>, <identifier>;");
		}
		
		current = this.scanner.scan();
		if (current.getType() != JEdjeToken.IDENTIFIER) {
			throw new JEdjeException("Expecting <reference>, <identifier>;");
		}
		String name = current.getValue();
		
		current = this.scanner.scan();
		if (current != JEdjeToken.SEMI_COLLON) {
			throw new JEdjeException("Expecting <reference>, <identifier>;");
		}
		return resolvePart(name);
	}

	private JEdjePart resolvePart(String name) throws JEdjeException {
		JEdjePart result = null;
		Enumeration enumeration = this.parts.elements();
		while (enumeration.hasMoreElements()) {
			JEdjePart part = (JEdjePart) enumeration.nextElement();
			if (part.getName().equals(name)) {
				result = part;
				break;
			}
		}
		
		if (result == null) {
			throw new JEdjeException("Can not resolve reference to part " + name + ".");
		}
		
		return result;
	}

	private JEdjeColor parseColor() throws IOException, JEdjeException {
		current = this.scanner.scan();
		if (current != JEdjeToken.COMMA) {
			throw new JEdjeException("Expecting color, r g b a;");
		}
		
		int[] color = new int[4];
		for (int i = 0; i < color.length; i++) {			
			current = this.scanner.scan();
			if (current.getType() != JEdjeToken.INTEGER) {
				throw new JEdjeException("Expecting color, r g b a;");
			}
			color[i] = Integer.parseInt(current.getValue());
		}
		
		current = this.scanner.scan();
		if (current != JEdjeToken.SEMI_COLLON) {
			throw new JEdjeException("Expecting <event>, <identifier>;");
		}
		
		return new JEdjeColor(color[0], color[1], color[2], color[3]);
	}

	private boolean parseBooleanParam() throws IOException, JEdjeException {
		current = this.scanner.scan();
		if (current != JEdjeToken.COMMA) {
			throw new JEdjeException("Expecting <event>, <identifier>;");
		}
		
		current = this.scanner.scan();
		if (current.getType() != JEdjeToken.INTEGER) {
			throw new JEdjeException("Expecting <event>, <identifier>;");
		}
		boolean result = Integer.parseInt(current.getValue()) != 0;
		
		current = this.scanner.scan();
		if (current != JEdjeToken.SEMI_COLLON) {
			throw new JEdjeException("Expecting <event>, <identifier>;");
		}
		return result;
	}

	private int parsePartType() throws IOException, JEdjeException {
		current = this.scanner.scan();
		if (current != JEdjeToken.COMMA) {
			throw new JEdjeException("Expecting type, <identifier>;");
		}
		
		current = this.scanner.scan();
		if (current.getType() != JEdjeToken.IDENTIFIER) {
			throw new JEdjeException("Expecting type, <identifier>;");
		}
		int result = JEdjeDescription.parseType(current.getValue());
		
		current = this.scanner.scan();
		if (current != JEdjeToken.SEMI_COLLON) {
			throw new JEdjeException("Expecting type, <identifier>;");
		}
		return result;
	}

	private JEdjeProgram[] parseGroupPrograms() throws IOException, JEdjeException {
		current = this.scanner.scan();
		return null;
	}

	private String parseName() throws JEdjeException, IOException {
		current = this.scanner.scan();
		if (current != JEdjeToken.COMMA) {
			throw new JEdjeException("Expecting name, <identifier>;");
		}
		
		current = this.scanner.scan();
		if (current.getType() != JEdjeToken.IDENTIFIER) {
			throw new JEdjeException("Expecting name, <identifier>;");
		}
		String name = current.getValue();
		
		current = this.scanner.scan();
		if (current != JEdjeToken.SEMI_COLLON) {
			throw new JEdjeException("Expecting name, <identifier>;");
		}
		return name;
	}

	private JEdjeImage[] parseImages() throws IOException, JEdjeException {
		current = this.scanner.scan();
		if (current != JEdjeToken.IMAGES) {
			return null;
		}
		current = this.scanner.scan();
		if (current != JEdjeToken.LEFT_KEY) {
			throw new JEdjeException("Left key expected.");
		}
		Vector images = new Vector();
		parseImageEntries(images);
		
		if (current != JEdjeToken.RIGHT_KEY) {
			throw new JEdjeException("Right key expected.");
		}
		current = this.scanner.scan();
		
		JEdjeImage[] result = new JEdjeImage[images.size()];
		images.copyInto(result);
		return result;
	}

	private void parseImageEntries(Vector images) throws IOException, JEdjeException {
		current = this.scanner.scan();
		while (current == JEdjeToken.IMAGE) {
			current = this.scanner.scan();
			if (current != JEdjeToken.COMMA) {
				throw new JEdjeException("Expecting image, <identifier> <compression> <level>;");
			}
			
			current = this.scanner.scan();
			if (current.getType() != JEdjeToken.IDENTIFIER) {
				throw new JEdjeException("Expecting image, <identifier> <compression> <level>;");
			}
			String name = current.getValue();
			
			current = this.scanner.scan();
			if (current.getType() != JEdjeToken.IDENTIFIER) {
				throw new JEdjeException("Expecting image, <identifier> <compression> <level>;");
			}
			int storage = JEdjeImage.parseStorage(current.getValue());
			
			current = this.scanner.scan();
			int level = 0x00;
			if (current.getType() == JEdjeToken.IDENTIFIER) {				
				level = Integer.parseInt(current.getValue());
				current = this.scanner.scan();
			}
			if (current != JEdjeToken.SEMI_COLLON) {
				throw new JEdjeException("Expecting image, <identifier> <compression> <level>;");
			}
			
			images.addElement(new JEdjeImage(name, storage, level));
			current = this.scanner.scan();
		}
	}
}
