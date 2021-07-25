const { src, dest, series, parallel } = require('gulp');
const del = require('del');
const log = require('fancy-log');
const fs = require('fs');

const paths = {
  react_src: 'build/**/*',
  react_dist: '../resources/static/'
};

function clean()  {
  log('removing the old files in the directory')
  return del('../resources/static/**', {force:true});
}

function copyReactCodeTask() {
  log('copying React code into the directory')
  return src(`${paths.react_src}`)
        .pipe(dest(`${paths.react_dist}`));
}

exports.default = series(
  clean,
  copyReactCodeTask
);