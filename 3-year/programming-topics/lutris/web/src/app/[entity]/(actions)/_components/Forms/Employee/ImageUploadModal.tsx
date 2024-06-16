import { type ChangeEvent, useRef, useState, useEffect } from "react";
import api from "~/api";

interface ImageUploadModalProps {
  employeeId: number;
  onUpload: () => void;
  onClose: () => void;
}

function ImageUploadModal({
  employeeId,
  onUpload,
  onClose,
}: ImageUploadModalProps) {
  const fileInput = useRef<HTMLInputElement>(null);
  const dialogRef = useRef<HTMLDialogElement>(null);

  useEffect(() => {
    dialogRef.current?.showModal();
  }, []);

  async function onSelectFile(event: ChangeEvent<HTMLInputElement>) {
    const { files } = event.target;

    if (!files) {
      return;
    }

    const image = files[0];

    console.log({ image });

    await api.employees.upload({ employeeId, image });

    onUpload();
    onClose();
  }

  return (
    <dialog
      className="bg-zinc-800 text-start text-gray-200 px-6 py-4 rounded-md max-w-lg"
      ref={dialogRef}
    >
      <h2 className="text-2xl mb-4 font-bold">Enviar imagem</h2>
      <p>Selecione a imagem desejada</p>
      <input
        ref={fileInput}
        type="file"
        className="hidden"
        onChange={onSelectFile}
        accept="image/png, image/jpeg, image/jpg"
      />
      <button
        className="px-6 py-3 mt-4 transition-all duration-300 hover:bg-emerald-800 bg-emerald-600 rounded-md"
        onClick={() => {
          fileInput.current?.click();
        }}
      >
        Enviar
      </button>
    </dialog>
  );
}

export default ImageUploadModal;
