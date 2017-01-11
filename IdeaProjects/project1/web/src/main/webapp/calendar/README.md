# jQuery Availability Calendar

A simple jQuery month calendar to show availability.


## Example Usage

#### HTML:

```html
<body>
	<div id="calendar"></div>
</body>
```

#### JS:

```javascript
var unavailableDates = [
	{start: '2015-08-01', end: '2015-08-07'},
	{start: '2015-09-11', end: '2015-09-15'},
	{start: '2015-09-15', end: '2015-09-23'}
];

$('#calendar').availabilityCalendar(unavailableDates);
```
