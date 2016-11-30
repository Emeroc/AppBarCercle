var format = {
	toInt: function(str) {
		var result = parseInt(str);
		return isNaN(result) ? 0 : result;
	},
	
	toFloat: function(str) {
		var result = parseFloat(str);
		return isNaN(result) ? 0 : result;
	},
	
	toEuros: function(prix) {
		return this.toFloat(prix).toFixed(2).replace('.', ',');
	},
	
	toDate: function(date) {
		var match = date.match(/([0-9]{4})-([0-9]{2})-([0-9]{2})T([0-9:]{8})/);
		return match[3] + '/' + match[2] + '/' + match[1] + ' - ' + match[4];
	}
};