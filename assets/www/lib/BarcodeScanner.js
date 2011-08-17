/**
 * @author hbb
 */

var BarcodeScanner = {
	scan: function( options )
	{
		return PhoneGap.exec(
				options.success,
				options.failure,
				'BarcodeScanner',
				'scan',
				[]
			);
	}
};
