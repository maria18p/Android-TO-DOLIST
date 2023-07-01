import mongoose from "mongoose";

const Schema = mongoose.Schema;

const noteSchema = new Schema({
	_id: mongoose.Schema.Types.ObjectId,
	title: {
		type: String,
		required: true,
	},
	text: String,
	createdAt: {
		type: Date,
		default: Date.now,
	},
	updatedAt: {
		type: Date,
		default: Date.now,
	},
	// status: {
	//   type: Boolean,
	//   default: true,
	// },
});

export const connectNoteModel = async (ODM) => {
	await ODM.model("Note", noteSchema);
};
