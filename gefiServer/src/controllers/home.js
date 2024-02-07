const database = require('../models');
const validacoes = require('../utils/validador');
const { InvalidArgumentError, HttpException } = require('../utils/erros');

class HomeController {

  static async home(req, res) {
    res.sendFile(`${process.cwd()}/public/gefi-web/index.html`);
  }

  static async login(req, res) {
    res.sendFile(`${process.cwd()}/public/gefi-web/index.html`);
  }
  static async equipamento(req, res) {
    res.sendFile(`${process.cwd()}/public/gefi-web/index.html`);
  }
  static async controle(req, res) {
    res.sendFile(`${process.cwd()}/public/gefi-web/index.html`);
  }
  static async usuario(req, res) {
    res.sendFile(`${process.cwd()}/public/gefi-web/index.html`);
  }
  static async pendencia(req, res) {
    res.sendFile(`${process.cwd()}/public/gefi-web/index.html`);
  }
  static async solicitacao(req, res) {
    res.sendFile(`${process.cwd()}/public/gefi-web/index.html`);
  }
  static async area(req, res) {
    res.sendFile(`${process.cwd()}/public/gefi-web/index.html`);
  }

  static async default(req, res) {
    res.sendFile(`${process.cwd()}/public/gefi-web/index.html`);
  }



}

module.exports = HomeController
