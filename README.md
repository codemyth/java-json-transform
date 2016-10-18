#Proteus, Json transformation using intuitive templates

Proteus is a small library that can be used to transform a JSON data of one structure to another, using defined templates. It can be added to any existing java project with no effort


##Up and Running

- Get an instance of proteus client and start transforming JSONs
```
// get an instance of proteus client
ProteusClient proteus = ProteusClient.getInstance();

// start executing commands on proteus client using one of several methods exposed
output_jsonobject = proteus.transform(input_jsonobject, template);

List<output_jsonobject> = proteus.transform(List<input_jsonobject>, template);
output_jsonarray = proteus.transform(input_jsonarray, template);
```

- You can play with Example.java to see the transformation in action (for the included example Json input and templates)


##Template Definition Guide

```
{
	"First Name": "First Name",
	"Last Name": "Last Name",
	"Address": {
		"Street": "Address",
		"City": "City",
		"Country": "Country",
		"District": "District"
	},
	"Rentals": "_asSet(Rentals.Film Title AS Film Title,Rentals.Payments AS Payments)"
}
```

Defining a template is as simple as creating a plain json text/object containing the key mappings. Above is a sample template and the details are explained below

- Elements can be nested to any level
- Actual values that will be substitued for a given key, is defined as the value. this key is the actual element in the input json to be transformed. Values in input json can be represented using dot for each lower level
- asSet is a keyword to group more than one elements together. This is needed when the resulting values are array. In the example above, Rentals field will have a json array as result with the included json object having fields film title, and payments. (Note: for asSet to work, the included fields should all return the exact same number of items for a given row. This is a reasonable and obvious  business use case scenario) 
- Setup and execute the included example project to see it in action
- AS is a keyword to give alias for theelements in sub json document. It is optional. If an explicit alias is omitted, last element key will be used by default. Play with the included examples


##About the Codename
Proteus was a primordial deity in Greek mythology, protector of the seas, rivers and other bodies of water. He is versatile, mutable and capable of assuming many different forms. Because Proteus could assume whatever shape he pleased, he came to be regarded by some as a symbol of the original matter from which the world was created. The word protean, one meaning of which is “changeable in shape or form,” is derived from Proteus.

##Licence and Terms

This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 
 This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 
 You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>