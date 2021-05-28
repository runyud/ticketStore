/**
 * 
 */

function checkBillingAddress() {
	if($("#theSameAsShippingAddress").is(":checked")) {
		$(".billingAddress").prop("disabled", true);
	} else {
		$(".billingAddress").prop("disabled", false);
	}
}

function checkPasswordMatch() {
	var password = $("#txtNewPassword").val();
	var confirmPassword = $("#txtConfirmPassword").val();

	if(password === "" && confirmPassword === "") {
		$("#checkPasswordMatch").html("");
		$("#updateUserInfoButton").prop("disabled", true);
	} else {
		if(password !== confirmPassword) {
			$("#checkPasswordMatch").html("Passwords do not match!");
			$("#updateUserInfoButton").prop("disabled", true);
		} else {
			$("#checkPasswordMatch").html("Passwords match!");
			$("#updateUserInfoButton").prop("disabled", false);
		}
	}

}

$(document).ready(function(){
	$(".cartItemQty").on('change', function(){
		var id = this.id;
		console.log("test");
		$('#update-item-' + id).css('display','inline-block');
	});
	$("#theSameAsShippingAddress").on('click', checkBillingAddress);
	$("#txtConfirmPassword").keyup(checkPasswordMatch);
	$("#txtNewPassword").keyup(checkPasswordMatch);
});