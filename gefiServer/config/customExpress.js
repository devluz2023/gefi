const express = require('express');
const cors = require('cors');
const routes = require('../src/routes');
const path = require('path');


module.exports = () => {

	const app = express();
	app.use(cors("*"));
	app.use(express.static(`${process.cwd()}/public/gefi-web/`));


	routes(app)

 return app
}