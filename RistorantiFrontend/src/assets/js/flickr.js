restaurantsPhotos = undefined;

function jsonFlickrApi(json) {
  restaurantsPhotos = json;
}

function getRandomPhoto() {
  let arrayFoto = restaurantsPhotos.photos.photo;
  const unaFoto = arrayFoto[(Math.floor(Math.random() * arrayFoto.length))];
  return "http://farm" + unaFoto.farm + ".staticflickr.com/" + unaFoto.server + "/" + unaFoto.id + "_" + unaFoto.secret + ".jpg";
}
