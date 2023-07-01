import express from "express";
import {
	deleteNoteRequest,
	getAllNotesRequest,
	postNoteRequest,
	postRequestUpdateNote,
} from "../middleware/notes.js";
import { respond } from "./utility.js";
const Router = express.Router();

Router.post("/addNewNote", async (req, res) => {
	return respond(await postNoteRequest(req.body), res);
});

Router.post("/updateNote", async (req, res) => {
	return respond(await postRequestUpdateNote(req.body), res);
});

Router.get("/getAllNotes", async (req, res) => {
	return respond(await getAllNotesRequest(req.query), res);
});

Router.post("/deleteNote", async (req, res) => {
	return respond(await deleteNoteRequest(req.body), res);
});

export default Router;
