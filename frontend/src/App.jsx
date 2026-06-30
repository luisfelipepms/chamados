import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from './assets/vite.svg'
import heroImg from './assets/hero.png'
import './App.css'
import { getAtendimentos } from './api/atendimentos'
import { useFetch } from './hooks/useFetch'
import CardAtendimento from './components/CardAtendimento'

function App() {
  const { data: atendimentos, loading, error, refetch } = useFetch(
      () => getAtendimentos(),
      [],
      1000
    );

    if (loading) return <p>Carregando chamados...</p>;
    if (error) return <p className="text-red-500">Erro: {error}</p>;

    return (
      <div className="p-4">
        <h2 className="text-lg font-bold mb-4">Chamados Abertos</h2>
        <div className='grid grid-cols-4 gap-3'>
          {atendimentos?.map((atendimento) => (
            <CardAtendimento key={atendimento.id} atendimento={atendimento} onFinalizado={refetch}></CardAtendimento>
          ))}
        </div>
      </div>
    );
  }

export default App
