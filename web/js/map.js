var map;
function initialize() {
    var myLatlng = new google.maps.LatLng(37.9745524, 23.7309986);

    var myOptions = {
        zoom: 10,
        center: myLatlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    }
    map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);

    var marker = new google.maps.Marker({
        draggable: true,
        position: myLatlng,
        map: map,
        title: "Your location"
    });

    google.maps.event.addListener(marker, 'dragend', function (event) {
        document.getElementById("latitude").value = this.getPosition().lat();
        document.getElementById("longitude").value = this.getPosition().lng();
    });

}

//function pan() {
//
//    var myLatlng = new google.maps.LatLng(document.getElementById("lat").value, document.getElementById("lng").value);
//
//    var myOptions = {
//        zoom: 10,
//        center: myLatlng,
//        mapTypeId: google.maps.MapTypeId.ROADMAP
//    }
//    map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
//
//    var marker = new google.maps.Marker({
//        draggable: true,
//        position: myLatlng,
//        map: map,
//        title: "Your location"
//    });
//
//    google.maps.event.addListener(marker, 'dragend', function (event) {
//        document.getElementById("latitude").value = this.getPosition().lat();
//        document.getElementById("longitude").value = this.getPosition().lng();
//    });
//}


