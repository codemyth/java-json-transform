/*
 * Copyright (C) 2016  Arun Kumar Selvaraj

 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */

package org.ppythagoras.proteus.core.exception;

/**
 * Custom Exception from the framework/library.
 * 
 * @author Arun Kumar Selvaraj
 *
 */
public class ProteusException extends RuntimeException {

	public ProteusException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProteusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ProteusException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ProteusException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ProteusException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
