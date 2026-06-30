import { useState } from "react";
import { finalizarAtendimento } from "../api/atendimentos";

function CardAtendimento({ atendimento, onFinalizado }) {
const [finalizando, setFinalizando] = useState(false);
  const statusStyles = {
    ABERTO: "bg-blue-100 text-blue-800",
    EM_ATENDIMENTO: "bg-amber-100 text-amber-800",
    FINALIZADO: "bg-green-100 text-green-800",
    CANCELADO: "bg-red-100 text-red-800",
  };

  const statusLabels = {
    ABERTO: "Aberto",
    EM_ATENDIMENTO: "Em atendimento",
    FINALIZADO: "Finalizado",
    CANCELADO: "Cancelado",
  };

  const dataFormatada = new Date(atendimento.dataCriacao).toLocaleString("pt-BR", {
    day: "2-digit",
    month: "2-digit",
    year: "numeric",
    hour: "2-digit",
    minute: "2-digit",
  });

const handleFinalizar = async () => {
    setFinalizando(true);
    try {
      await finalizarAtendimento(atendimento.id);
      onFinalizado(); // avisa o pai pra recarregar a lista
    } catch (err) {
      alert("Erro ao finalizar atendimento: " + err.message);
    } finally {
      setFinalizando(false);
    }
  };

  return (
    <div className="max-w-sm bg-gray-300 border border-gray-200 rounded-xl p-5 flex flex-col gap-3 shadow-sm">
      <div className="flex items-center justify-between">
        <span className="text-sm text-gray-500">{dataFormatada}</span>
        <span
          className={`text-xs font-medium px-3 py-1 rounded-full ${
            statusStyles[atendimento.statusAtendimento] ?? "bg-gray-100 text-gray-700"
          }`}
        >
          {statusLabels[atendimento.statusAtendimento] ?? atendimento.statusAtendimento}
        </span>
      </div>

      <div className="border-t border-gray-100 pt-3 items-center gap-2 grid grid-cols-2">
        <span className="text-sm font-medium text-gray-800">
          {atendimento.timeAtendimento}
        </span>
              {atendimento.statusAtendimento === "EM_ATENDIMENTO" && (
            <button onClick={handleFinalizar} className="text-xs cursor-pointer font-medium px-3 py-1 rounded-md bg-blue-100 text-blue-800">
            Finalizar
            </button>
        )}
      </div>
    </div>
  );
}

export default CardAtendimento;