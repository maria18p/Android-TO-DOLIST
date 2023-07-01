import mongoose from "mongoose";
import dotenv from "dotenv";
import { connectNoteModel } from "../models/Note_schema.js";
dotenv.config();

const connectMongoose = async () => {
	mongoose.set({ strictQuery: false });
	const mongo_uri = process.env.DB_URL;
	mongoose.set({ strictQuery: false });
	return await mongoose
		.connect(mongo_uri)
		.then((result) => {
			return result;
		})
		.catch((err) => {
			console.log(err);
		});
};

export const setupODM = async () => {
	const dbConnection = await connectMongoose();
	await connectNoteModel(dbConnection);
	return dbConnection;
};

export default connectMongoose;
