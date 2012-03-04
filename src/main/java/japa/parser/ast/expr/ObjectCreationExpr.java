/*
 * Copyright (C) 2007 Júlio Vilmar Gesser.
 * 
 * This file is part of Java 1.5 parser and Abstract Syntax Tree.
 *
 * Java 1.5 parser and Abstract Syntax Tree is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Java 1.5 parser and Abstract Syntax Tree is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Java 1.5 parser and Abstract Syntax Tree.  If not, see <http://www.gnu.org/licenses/>.
 */
/*
 * Created on 05/10/2006
 */
package japa.parser.ast.expr;

import japa.parser.ast.body.BodyDeclaration;
import japa.parser.ast.type.ClassOrInterfaceType;
import japa.parser.ast.type.Type;
import japa.parser.ast.visitor.GenericVisitor;
import japa.parser.ast.visitor.VoidVisitor;

import java.util.Iterator;
import java.util.List;

/**
 * @author Julio Vilmar Gesser
 */
public final class ObjectCreationExpr extends Expression {

	private Expression scope;

	private ClassOrInterfaceType type;

	private List<Type> typeArgs;

	private List<Expression> args;

	private List<BodyDeclaration> anonymousClassBody;

	public ObjectCreationExpr() {
	}

	public ObjectCreationExpr(final Expression scope, final ClassOrInterfaceType type, final List<Expression> args) {
		setScope(scope);
		setType(type);
		setArgs(args);
	}

	public ObjectCreationExpr(final int beginLine, final int beginColumn, final int endLine, final int endColumn,
			final Expression scope, final ClassOrInterfaceType type, final List<Type> typeArgs,
			final List<Expression> args, final List<BodyDeclaration> anonymousBody) {
		super(beginLine, beginColumn, endLine, endColumn);
		setScope(scope);
		setType(type);
		setTypeArgs(typeArgs);
		setArgs(args);
		setAnonymousClassBody(anonymousBody);
	}

	@Override public <R, A> R accept(final GenericVisitor<R, A> v, final A arg) {
		return v.visit(this, arg);
	}

	@Override public <A> void accept(final VoidVisitor<A> v, final A arg) {
		v.visit(this, arg);
	}

	public List<BodyDeclaration> getAnonymousClassBody() {
		return anonymousClassBody;
	}

	public List<Expression> getArgs() {
		return args;
	}

	public Expression getScope() {
		return scope;
	}

	public ClassOrInterfaceType getType() {
		return type;
	}

	public List<Type> getTypeArgs() {
		return typeArgs;
	}

	public void setAnonymousClassBody(final List<BodyDeclaration> anonymousClassBody) {
		this.anonymousClassBody = anonymousClassBody;
		if ( this.anonymousClassBody != null){
			Iterator<BodyDeclaration> it = anonymousClassBody.iterator();
			while(it.hasNext()){
				BodyDeclaration current = it.next();
				current.setParentNode(this);
			}
		}
	}

	public void setArgs(final List<Expression> args) {
		this.args = args;
		if(this.args != null ){
			Iterator<Expression> it = args.iterator();
			while(it.hasNext()){
				Expression current = it.next();
				current.setParentNode(this);
			}
		}
	}

	public void setScope(final Expression scope) {
		this.scope = scope;
		if(this.scope != null){
			this.scope.setParentNode(this);
		}
	}

	public void setType(final ClassOrInterfaceType type) {
		this.type = type;
		if(this.type != null){
			this.type.setParentNode(this);
		}
	}

	public void setTypeArgs(final List<Type> typeArgs) {
		this.typeArgs = typeArgs;
		if(this.typeArgs!=null){
			Iterator<Type> it = typeArgs.iterator();
			while(it.hasNext()){
				Type current = it.next();
				current.setParentNode(this);
			}
		}
	}

}
