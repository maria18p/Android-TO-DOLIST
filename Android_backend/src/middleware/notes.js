import { Error } from "mongoose";
import { setupODM } from "../config/setupMongoDB.js";
import {
	addNote,
	getNotes,
	removeNote,
	updateNote,
} from "../controllers/note_actions.js";

let ODM;

export const createDBConnection = async () => {
	ODM = await setupODM();
	console.log("SERVER READY");
};

// ============================== Utility functions ==============================

export const requestSuccess = async (data) => {
	data.success = true;
	return {
		success: true,
		json: data,
	};
};

export const requestFailure = async (data) => {
	data.success = false;
	return {
		success: false,
		json: data,
	};
};

export const postNoteRequest = async (req) => {
	const result = await addNote(req);
	return result !== null
		? requestSuccess({ data: result })
		: requestFailure({ message: result.message });
};

export const postRequestUpdateNote = async (req) => {
	const queryResult = await updateNote(req);
	return queryResult.success
		? requestSuccess({ message: queryResult.message })
		: requestFailure({ message: queryResult.message });
};

export const getAllNotesRequest = async (req) => {
	const result = await getNotes(req.filter);
	return requestSuccess({ data: result });
};

export const deleteNoteRequest = async (req) => {
	const queryResult = await removeNote(req);
	return queryResult.success
		? requestSuccess({ message: queryResult.message })
		: requestFailure({ message: queryResult.message });
};

export { ODM };
